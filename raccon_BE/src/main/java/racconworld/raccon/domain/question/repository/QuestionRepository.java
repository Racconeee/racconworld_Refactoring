package racconworld.raccon.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.question.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query("SELECT q FROM Question q JOIN FETCH q.choices WHERE q.questionToTest.id = :testId")
    List<Question> findQuestionsWithChoicesByTestId(@Param("testId") Long testId);

}
