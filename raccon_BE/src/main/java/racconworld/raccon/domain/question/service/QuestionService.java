package racconworld.raccon.domain.question.service;

import racconworld.raccon.domain.question.dto.Response.DetailQuizResDto;

public interface QuestionService  {

    DetailQuizResDto showDetailQuiz(Long testId) ;

}
