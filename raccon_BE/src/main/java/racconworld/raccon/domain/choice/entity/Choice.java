package racconworld.raccon.domain.choice.entity;

import jakarta.persistence.*;


import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import racconworld.raccon.domain.question.entity.Question;

@ToString
@Entity
@Getter
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "dtype")
@Table(name = "choice")
public abstract class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question choiceToQuestion;

    private String choiceText;

    public Choice(Question choiceToQuestion, String choiceText) {
        this.choiceToQuestion = choiceToQuestion;
        this.choiceText = choiceText;
    }

    public abstract Object getScore();
}
