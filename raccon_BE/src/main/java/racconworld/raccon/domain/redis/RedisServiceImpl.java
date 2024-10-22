package racconworld.raccon.domain.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.visit.repository.VisitRepository;
import racconworld.raccon.domain.visit.service.VisitService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
    private final RedisTemplate<String, Object> redisTemplate;
    private final TestRepository testRepository;  // DB 접근을 위한 Repository
    private final VisitRepository visitRepository;
    private final ObjectMapper objectMapper;  // ObjectMapper 주입


    //조회수 관련 -------------------------------------------------


    //ObjectMapper를 안하고 햇을때 문제가 발생한 이슈에 대해서 정리하자
    //그냥 하면 안되고 dto로 만들고 해야함  redis 저장 할 때 DTO 형태로 저장 했기 때문에
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void syncViewCountsToDB() {
        log.info("Redis -- 조회수 동기화 시작");

        Set<String> keys = redisTemplate.keys("getTestView::*");
        if (keys != null) {
            log.info("조회된 Redis 키: " + keys);

            for (String key : keys) {
                Long testId = Long.parseLong(key.split(":")[2]);  // Redis 키에서 testId 추출
                log.info("처리 중인 testId: " + testId);

                // Redis 해시에서 'view' 필드 값 가져오기
                Object viewCountObj = redisTemplate.opsForHash().get(key, "view");

                if (viewCountObj != null) {
                    try {
                        Long viewCount = Long.parseLong(viewCountObj.toString());

                        // 데이터베이스 업데이트 전에 추가 로그 출력
                        log.info("DB 업데이트 시작 - 테스트 ID: " + testId + ", 조회수: " + viewCount);

                        // DB에 조회수 업데이트
                        testRepository.updateTestByView(testId, viewCount);

                        // Redis에서 해당 키 삭제
                        redisTemplate.delete(key);

                        log.info("업데이트 완료 - 테스트 ID: " + testId + ", 조회수: " + viewCount);

                        redisTemplate.opsForHash().put(key, "testId", String.valueOf(testId));
                        redisTemplate.opsForHash().put(key, "view", String.valueOf(viewCount));
                    } catch (NumberFormatException e) {
                        log.error("조회수 변환 중 오류 발생: " + e.getMessage());
                    }
                } else {
                    log.warn("해당 키에서 'view' 필드를 찾을 수 없음: " + key);
                }
            }
        }
    }

    public void saveTestViewInRedis(Long testId) {
        String redisKey = "getTestView::" + testId;
        Long viewCount = testRepository.findViewCountById(testId);
        redisTemplate.opsForHash().put(redisKey, "testId", String.valueOf(testId));
        redisTemplate.opsForHash().put(redisKey, "view", String.valueOf(viewCount));
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
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void syncTotalTestViewCountToDB2() {
        log.info("Redis -- 전체 방문수 동기화 시작");

        String redisKey = "getTestTotalVisit";
        String totalVisitedStr = (String) redisTemplate.opsForHash().get(redisKey, "total");

        if (totalVisitedStr != null) {
            Long totalView = testRepository.findAllByView();
            redisTemplate.opsForHash().put(redisKey, "total", String.valueOf(totalView));
        }

    }


    public Long totalTestViewCount() {
        String redisKey = "getTestTotalVisit";
        String totalVisitedStr = (String) redisTemplate.opsForHash().get(redisKey, "total");

        if(totalVisitedStr == null) {
            Long totalView = testRepository.findAllByView();
            redisTemplate.opsForHash().put(redisKey, "total", String.valueOf(totalView));

            totalVisitedStr = (String) redisTemplate.opsForHash().get(redisKey, "total");

        }
        return Long.parseLong(totalVisitedStr);
    }

}