package racconworld.raccon.domain.question.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.question.dto.Response.DetailPersonalQuizRes;
import racconworld.raccon.domain.question.dto.Response.DetailScoreQuizRes;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.question.repository.QuestionRepository;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;


    @Transactional
    public String showDetailQuiz(Long testId , String testType) throws Exception {


        Test testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new Exception("Test with ID " + testId + " not found"));


        List<Question> DetailQuizList = questionRepository.findQuestionsWithChoicesByTestId(testId);


        return "성공";
    };
}
