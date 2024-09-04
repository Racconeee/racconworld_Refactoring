package racconworld.raccon.domain.question.service;


import racconworld.raccon.domain.question.dto.Response.DetailPersonalityQuizResDto;
import racconworld.raccon.domain.question.dto.Response.DetailScoreQuizResDto;
import racconworld.raccon.domain.test.entity.Test;

public interface QuestionService  {

    DetailScoreQuizResDto showDetailScoreQuiz(Long testId) ;
    DetailPersonalityQuizResDto showDetailPersonalityQuiz(Long testId);

}
