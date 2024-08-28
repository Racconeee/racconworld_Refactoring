package racconworld.raccon.domain.test;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.question.Question;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @OneToMany(mappedBy = "question_to_test", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @Column(nullable = false)
    private String testName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)

    @Column
    private String filePath;
    @Column
    private String fileDownload;

    @Builder
    public Test(String testName, TestType testType) {
        this.testName = testName;
        this.testType = testType;
    }

    public enum TestType {
        SCORE, // 점수 기반 테스트
        PERSONALITY // 성격 유형 테스트
    }

}
