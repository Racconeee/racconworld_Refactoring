package racconworld.raccon.domain.choice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.choice.entity.Choice;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
