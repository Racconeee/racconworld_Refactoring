package racconworld.raccon.domain.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.log.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
