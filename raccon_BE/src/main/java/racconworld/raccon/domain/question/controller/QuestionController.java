package racconworld.raccon.domain.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.question.dto.Response.DetailPersonalityQuizResDto;
import racconworld.raccon.domain.question.dto.Response.DetailScoreQuizResDto;
import racconworld.raccon.domain.question.service.QuestionService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/quiz")
public class QuestionController {

    private final QuestionService questionService;

    //기본 동작 로직 testId , testType을 받아서
    //해당하는 테스트 퀴즈 상세 조회
    //테스트 ,퀴즈 ,선택지 정보 제공 결국 테스트하는데 필요한 데이터 전부 제공한다.
    @GetMapping("/showDetail/score/{testId}")
    public ResponseEntity<BaseResponse<DetailScoreQuizResDto>> showDetailScoreQuiz(@PathVariable Long testId) throws Exception {

        DetailScoreQuizResDto detailScoreQuizResDto = questionService.showDetailScoreQuiz(testId);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                detailScoreQuizResDto
        );

    }
    @GetMapping("/showDetail/personality/{testId}")
    public ResponseEntity<BaseResponse<DetailPersonalityQuizResDto>> showDetailPersonalityQuiz(@PathVariable Long testId) throws Exception {

        DetailPersonalityQuizResDto detailPersonalityQuizResDto = questionService.showDetailPersonalityQuiz(testId );

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                detailPersonalityQuizResDto
        );
    }
}
