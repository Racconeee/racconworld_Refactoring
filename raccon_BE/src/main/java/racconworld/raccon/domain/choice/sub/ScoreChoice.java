package racconworld.raccon.domain.choice.sub;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import racconworld.raccon.domain.choice.Choice;

@Entity
@DiscriminatorValue("Score")
public class ScoreChoice extends Choice {

    private Long score;

}
