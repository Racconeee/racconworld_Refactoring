package racconworld.raccon.domain.result.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.result.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {
}
