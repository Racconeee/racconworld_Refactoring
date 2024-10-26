package racconworld.raccon.domain.question.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.question.dto.Response.DetailQuizResDto;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.question.repository.QuestionRepository;
import racconworld.raccon.domain.redis.RedisService;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.visit.repository.VisitRepository;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final RedisService redisService;

    @Override
    @Transactional
    @Cacheable(cacheNames = "showDetailQuiz", key = "'testId:' + #p0", cacheManager = "cacheManager")
    public DetailQuizResDto showDetailQuiz(Long testId ) {
        Test testEntity = getTestEntityByIdAndUpdateTestAndVisit(testId);
        List<Question> detailQuizList = questionRepository.findQuestionsWithChoicesByTestId(testId);

        return DetailQuizResDto.toDto(testEntity , detailQuizList);
    };


    private Test getTestEntityByIdAndUpdateTestAndVisit(Long testId) {
        Test testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new CustomExceptionHandler(ErrorCode.TEST_NOT_FOUND));
        //특정 Test에 대한 방문자수 증가
//        testRepository.updateTestByView(testId);
        //위에서 찾은 Test 갹체를 넘기려고했는데 이렇게 하면 JPA가 특정 필드가 아닌 전체를 넘기려고함
        //그래서 차라리 쿼리문안에 정적으로 고정시킴
        //전체 페이지에 대한 방문자수 증가
        //필요할때 마다 더해서 보내는걸로 리펙토링함
//        visitRepository.incrementVisitCount();


        return testEntity;
    }
}
