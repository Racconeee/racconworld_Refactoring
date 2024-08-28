package racconworld.raccon.domain.choice;

import jakarta.persistence.*;


import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.question.Question;

@Entity
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public abstract class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question choice_to_question;

    @Column(nullable = false)
    private String choiceText;

}
