package racconworld.raccon.domain.question.service;


import racconworld.raccon.domain.question.dto.Response.DetailPersonalityQuizResDto;
import racconworld.raccon.domain.question.dto.Response.DetailScoreQuizResDto;

public interface QuestionService  {

    DetailScoreQuizResDto showDetailScoreQuiz(Long test_id) throws Exception;
    DetailPersonalityQuizResDto showDetailPersonalityQuiz(Long test_id) throws Exception;

}
