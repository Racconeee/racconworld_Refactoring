package racconworld.raccon.domain.question.entity;


import jakarta.persistence.*;
import lombok.*;
import racconworld.raccon.domain.choice.entity.Choice;
import racconworld.raccon.domain.test.entity.Test;

import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test questionToTest;

    @OneToMany(mappedBy = "choiceToQuestion", cascade = CascadeType.ALL)
    private List<Choice> choices = new ArrayList<>();

    @Column(nullable = false)
    private String questionText;

    @Builder
    public Question(Test questionToTest, String questionText) {
        this.questionToTest = questionToTest;
        this.questionText = questionText;
    }
}
