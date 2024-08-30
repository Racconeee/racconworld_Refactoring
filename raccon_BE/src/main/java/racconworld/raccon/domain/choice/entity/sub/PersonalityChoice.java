package racconworld.raccon.domain.choice.entity.sub;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.choice.entity.Choice;
import racconworld.raccon.domain.question.entity.Question;

@Entity
@Getter
@DiscriminatorValue("Personality")
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class PersonalityChoice extends Choice {

    private String personality;

    @Builder
    public PersonalityChoice(Question choiceToQuestion, String choiceText, String personality) {
        super(choiceToQuestion, choiceText);
        this.personality = personality;
    }

    @Override
    public String getScore(){
        return this.personality;
    }
}
