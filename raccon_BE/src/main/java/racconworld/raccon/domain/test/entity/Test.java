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
    private String view;
    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "varchar(15)")
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)
    private String filePath;
    private String fileDownload;


    @Builder
    public Test(List<Question> questions, String testName, TestType testType, String filePath, String fileDownload) {
        this.questions = questions;
        this.testName = testName;
        this.testType = testType;
        this.filePath = filePath;
        this.fileDownload = fileDownload;
    }
}
