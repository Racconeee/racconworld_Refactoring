package racconworld.raccon.domain.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racconworld.raccon.domain.question.service.QuestionService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/quiz")
public class QuestionController  {

    private final QuestionService questionService;

    @GetMapping("/{testId}/{testType}")
    public ResponseEntity<String> findDetailQuiz(@PathVariable Long testId,
                                                 @PathVariable String testType) throws Exception {
        log.info("testId : {} " , testId);
        questionService.showDetailQuiz(testId ,testType );

        return ResponseEntity.ok("성공");
    }



}
