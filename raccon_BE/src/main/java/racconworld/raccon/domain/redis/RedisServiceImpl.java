package racconworld.raccon.domain.redis;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;
import racconworld.raccon.domain.test.repository.TestRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
    private final RedisTemplate<String, Object> redisTemplate;
    private final TestRepository testRepository;  // DB 접근을 위한 Repository


    //조회수 관련 -------------------------------------------------


    //ObjectMapper를 안하고 햇을때 문제가 발생한 이슈에 대해서 정리하자
    //그냥 하면 안되고 dto로 만들고 해야함  redis 저장 할 때 DTO 형태로 저장 했기 때문에
    @Scheduled(fixedRate = 600000)
    @Transactional
    public void syncViewCountsToDB() {

        Set<String> keys = redisTemplate.keys("getTestView::*");
        if (keys != null) {

            for (String key : keys) {
                Long testId = Long.parseLong(key.split(":")[2]);  // Redis 키에서 testId 추출

                // Redis 해시에서 'view' 필드 값 가져오기
                Object viewCountObj = redisTemplate.opsForHash().get(key, "view");

                if (viewCountObj != null) {
                    try {
                        Long viewCount = Long.parseLong(viewCountObj.toString());


                        // DB에 조회수 업데이트
                        testRepository.updateTestByView(testId, viewCount);

                    } catch (NumberFormatException e) {
                        log.error("조회수 변환 중 오류 발생: " + e.getMessage());
                    }
                } else {
                }
            }
        }
    }


    //redis에서 가져온 겂은 먼저 String으로 변환 후 classCasing해야함
    public ShowTestViewListResDto getViewCount(Long testId) {
        String redisKey = "getTestView::" + testId;

        // Redis에서 조회수 가져오기 (String으로 먼저 변환)
        String viewCountStr = (String) redisTemplate.opsForHash().get(redisKey, "view");

        Long viewCount = null;

        // 만약 Redis에서 조회수가 없을 경우
        if (viewCountStr == null) {
            // DB에서 조회수 가져오기
            Long testView = testRepository.findViewCountById(testId);
            viewCount = testView;

            // Redis에 저장
            redisTemplate.opsForHash().put(redisKey, "testId", String.valueOf(testId));
            redisTemplate.opsForHash().put(redisKey, "view", String.valueOf(testView));
        } else {
            // Redis에서 가져온 String 데이터를 Long으로 변환
            viewCount = Long.parseLong(viewCountStr);
        }

        return new ShowTestViewListResDto(testId, viewCount);
    }


    public void incrementViewCount(Long testId) {
        String redisKey = "getTestView::" + testId;
        redisTemplate.opsForHash().increment(redisKey, "view", 1);
    }


    //전체 방문자 수 관련 -------------------------------------------------

    //없으면 동기화 안함
    //생각을 해보니 굳이 이거를 DB에서 뒤져서 가져오는 것 보다 그냥 Redis에서 합쳐서 하는게 훨씬 나을듯 싶다.
    //나중에 데이터가 늘어나는 것을 생각해도

    @Scheduled(fixedRate = 600000)
    public void syncTotalTestViewCount() {

        String redisKey = "getTestTotalVisit";

        Long totalViewCount = calculateTotalViewCount();
        if (totalViewCount != null) {
            redisTemplate.opsForHash().put(redisKey, "total", String.valueOf(totalViewCount));
        }
    }
    public Long calculateTotalViewCount() {
        Set<String> getTestViewKeys = redisTemplate.keys("getTestView::*");
        Long totalViewCount = 0L;
        if (getTestViewKeys != null) {
            for (String key : getTestViewKeys) {
                Object viewCountObj = redisTemplate.opsForHash().get(key, "view");
                if (viewCountObj != null) {
                    try {
                        Long viewCount = Long.parseLong(viewCountObj.toString());
                        totalViewCount += viewCount;
                    } catch (NumberFormatException e) {
                        log.error("조회수 변환 중 오류 발생: {}", e.getMessage());
                    }
                }
            }
        }
        return totalViewCount;
    }

    // 일반적으로 사용하는
    public Long totalTestViewCount() {
        String redisKey = "getTestTotalVisit";
        String totalVisitedStr = (String) redisTemplate.opsForHash().get(redisKey, "total");

        if (totalVisitedStr == null) {
            Long totalViewCount = calculateTotalViewCount();
            if (totalViewCount != null) {
                // Redis에 'total' 필드 업데이트
                redisTemplate.opsForHash().put(redisKey, "total", String.valueOf(totalViewCount));
                return totalViewCount;
            }
        }

        return Long.parseLong(totalVisitedStr);
    }



    @CacheEvict(cacheNames = "getTestList", allEntries = true, cacheManager = "cacheManager")
    public void clearTestListCache() {
    }



}
