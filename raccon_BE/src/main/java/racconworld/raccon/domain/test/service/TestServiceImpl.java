package racconworld.raccon.domain.test.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.redis.RedisService;
import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;
import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.test.dto.Response.TestTotalVisitResDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final RedisService redisService;

    @Override
    @Cacheable(cacheNames = "getTestList", key = "'pageNumber:' + #pageNumber",  cacheManager = "cacheManager")
    public ShowTestResDto getTestListByPage(int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC, "view"));
        Slice<Test> page = testRepository.findAll(pageRequest);
        List<ShowTestResDto.ShowTestListDto> showTestListDtos = page.map(t -> new ShowTestResDto.ShowTestListDto(t.getId() , t.getTestName() ,t.getView(), t.getFilePath())).stream().toList();

        return new ShowTestResDto(page.hasNext(), showTestListDtos);
    }

    @Override
//    @Cacheable(cacheNames = "getTestTotalVisit", key = "'TestTotalVisit'", cacheManager = "cacheManager")
    public TestTotalVisitResDto getTestTotalVisit() {
        Long totalVisitedView = redisService.totalTestViewCount();
        return new TestTotalVisitResDto(totalVisitedView);
    }


    // home 에 들어갔을 때 단순히 조회된 테스트에 대한 view 값 만 가져오기
    //이 때는 increment 안나감 단순히 DTO에 대해서 매핑해주려고 하는 것
//    public List<ShowTestListViewResDto> getTestViewCounts(List<String> testIds) {
//        // RedisService를 사용하여 조회수 정보를 가져옴
//        return redisService.getViewCounts(testIds);
//    }



    @Override
    public List<ShowTestViewListResDto> getTestViewList(List<Long> testIds) {
        List<ShowTestViewListResDto> result = new ArrayList<>();
        for (Long testId : testIds) {
            System.out.println("testId 호출" + testId);
            result.add(redisService.getViewCount(testId));
        }
        return result; // 결과 반환 (각 testId에 해당하는 조회수 맵)
    }



    //@Cacheable 최초의 반환된 값을 redis 의 값으로 저장한다.
    //그러니 이 메서드에서는 TestList 에서 데이터를 조회해와서 redis 에 저장하는 형식으로 하면 될거같다.
    //그러면 이 값을 모아서 다시 위의 메소드에서 처리하고 -> Controller로 나가보자
    //이렇게 해야 testId를 개별적으로 저장할 수 잇다.




}
