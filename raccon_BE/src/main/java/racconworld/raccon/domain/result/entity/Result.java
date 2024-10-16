package racconworld.raccon.domain.result.entity;

import jakarta.persistence.*;
import lombok.*;
import racconworld.raccon.domain.test.entity.Test;

@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "result")

public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "test_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Test resultToTest;

    @Column
    private String fileName;
    @Column
    private String filePath;
    @Column
    private String score;

    @Builder
    public Result(Test resultToTest, String fileName, String filePath, String score) {
        this.resultToTest = resultToTest;
        this.fileName = fileName;
        this.filePath = filePath;
        this.score = score;
    }

    public Result(Test resultToTest,String filePath,  String score) {
        this.resultToTest = resultToTest;
        this.filePath = filePath;
        this.score = score;
        this.score = score;
    }
}
