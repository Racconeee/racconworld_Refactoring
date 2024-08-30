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
@DiscriminatorValue("Score")
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class ScoreChoice extends Choice {

    private Long score;


    @Builder
    public ScoreChoice(Question choiceToQuestion, String choiceText, Long score) {
        super(choiceToQuestion, choiceText);
        this.score = score;
    }

    @Override
    public Long getScore(){
        return this.score;
    }
}
