package racconworld.raccon.domain.result.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.redis.RedisService;
import racconworld.raccon.domain.result.dto.Response.ShowResultResDto;
import racconworld.raccon.domain.result.entity.Result;
import racconworld.raccon.domain.result.repository.ResultRepository;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {


    private final ResultRepository repository;
    private final RedisService redisService;


    //나중에 캐시 히트 되는지 확인하기
    //cach hit 가 되면 incrementTestView
    @Override
    @Transactional
    @Cacheable(cacheNames = "ShowResultFilePath", key = "'testId:' + #p0 + ':score:' + #p1", cacheManager = "cacheManager")
    public ShowResultResDto showResult(Long testId, String score) {

        Result result = repository.findResultByTestIdAndScore(testId , score).orElseThrow(() ->
                new CustomExceptionHandler(ErrorCode.RESULT_NOT_FOUND));

        return new ShowResultResDto(result.getFilePath());
    }

    public void incrementTestView( Long testId ) {
        redisService.incrementViewCount(testId);
    }
}
