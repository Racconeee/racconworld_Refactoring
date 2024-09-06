package racconworld.raccon.domain.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.result.entity.Result;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {


    @Query("SELECT r From Result r WHERE r.resultToTest.id = :testId AND r.score = :score")
    Optional<Result> findResultByTestIdAndScore(@Param("testId") Long testId , @Param("score") String score);
}
