package racconworld.raccon.domain.question;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.choice.Choice;
import racconworld.raccon.domain.test.Test;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test question_to_test;

    @OneToMany(mappedBy = "choice_to_question", cascade = CascadeType.ALL)
    private List<Choice> choices = new ArrayList<>();

    @Column(nullable = false)
    private String questionText;

    @Builder
    public Question(Test question_to_test, String questionText) {
        this.question_to_test = question_to_test;
        this.questionText = questionText;
    }
}
