package racconworld.raccon.domain.test.entity;

import jakarta.persistence.*;
import lombok.*;
import racconworld.raccon.domain.question.entity.Question;

import java.util.ArrayList;
import java.util.List;

@ToString
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

    private String testName;
    private Long view;
    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "varchar(15)")
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)
    private String fileName;
    private String filePath;


    @Builder
    public Test(List<Question> questions, String testName, Long view, TestType testType, String fileName, String filePath) {
        this.questions = questions;
        this.testName = testName;
        this.view = view;
        this.testType = testType;
        this.fileName = fileName;
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
