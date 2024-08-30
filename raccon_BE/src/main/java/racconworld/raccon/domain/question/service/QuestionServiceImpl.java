package racconworld.raccon.domain.question.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.question.dto.Response.DetailPersonalityQuizResDto;
import racconworld.raccon.domain.question.dto.Response.DetailScoreQuizResDto;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.question.repository.QuestionRepository;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;


    @Transactional
    @Override
    public DetailScoreQuizResDto showDetailScoreQuiz(Long testId ) {
        Test testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new CustomExceptionHandler(ErrorCode.BAD_REQUEST,"해당 Test가 없습니다."));

        List<Question> detailQuizList = questionRepository.findQuestionsWithChoicesByTestId(testId);

        return DetailScoreQuizResDto.toDto(testEntity , detailQuizList);


    };


    @Transactional
    @Override
    public DetailPersonalityQuizResDto showDetailPersonalityQuiz(Long testId) throws Exception {
        Test testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new CustomExceptionHandler(ErrorCode.BAD_REQUEST,"해당 Test가 없습니다."));

        List<Question> detailQuizList = questionRepository.findQuestionsWithChoicesByTestId(testId);

        return DetailPersonalityQuizResDto.toDto(testEntity , detailQuizList);


    };
}
