package racconworld.raccon.domain.test.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.result.entity.Result;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @OneToMany(mappedBy = "questionToTest", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "resultToTest", cascade = CascadeType.ALL)
    private List<Result> results = new ArrayList<>();

    private String testName;
    private Long view;
    @Enumerated(EnumType.STRING)
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)
    private String filePath;


    @Builder
    public Test(List<Question> questions, String testName, Long view, TestType testType,  String filePath) {
        this.questions = questions;
        this.testName = testName;
        this.view = view;
        this.testType = testType;
        this.filePath = filePath;
    }

    public Test(String testName, Long view, TestType testType) {
        this.testName = testName;
        this.view = view;
        this.testType = testType;
    }
    public void uploadFilePath(String filePath) {
        this.filePath = filePath;
    }


}
