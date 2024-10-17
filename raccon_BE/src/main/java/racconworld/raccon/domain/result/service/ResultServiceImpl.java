package racconworld.raccon.domain.result.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    private final TestRepository testRepository;

    @Override
    public ShowResultResDto showResult( Long testId ,String score) {
        Result result = repository.findResultByTestIdAndScore(testId , score).orElseThrow(() ->
                new CustomExceptionHandler(ErrorCode.RESULT_NOT_FOUND));

        ShowResultResDto showRsultResDto = new ShowResultResDto( result.getFilePath());

        return showRsultResDto;
    }
}
