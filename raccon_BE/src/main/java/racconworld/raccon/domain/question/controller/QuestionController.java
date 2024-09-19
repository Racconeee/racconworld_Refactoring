package racconworld.raccon.domain.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.question.dto.Response.DetailQuizResDto;
import racconworld.raccon.domain.question.service.QuestionService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/quiz")
public class QuestionController {

    private final QuestionService questionService;

    //기본 동작 로직 testId , testType을 받아서
    //해당하는 테스트 퀴즈 상세 조회
    //테스트 ,퀴즈 ,선택지 정보 제공 결국 테스트하는데 필요한 데이터 전부 제공한다.
    //각각 데이터가 하나이니 PathVariable로 처리햇다. 직관적 url 기준 설계
    @GetMapping("/detail/{testId}")
    public ResponseEntity<BaseResponse<DetailQuizResDto>> showDetailQuiz(@PathVariable("testId") Long testId) throws Exception {

        log.info("testId : {} " , testId);
        DetailQuizResDto detailScoreQuizResDto = questionService.showDetailQuiz(testId);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                detailScoreQuizResDto
        );
    }
}
