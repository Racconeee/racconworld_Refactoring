package racconworld.raccon.domain.choice.sub;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import racconworld.raccon.domain.choice.Choice;

@Entity
@DiscriminatorValue("Personality")
public class PersonalityChoice extends Choice {


    private String Personality;

}
