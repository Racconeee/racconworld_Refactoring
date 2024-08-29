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
    private String filePath;
    @Column
    private String fileDownload;
    @Column
    private String score;

    @Builder

    public Result(Test resultToTest, String filePath, String fileDownload, String score) {
        this.resultToTest = resultToTest;
        this.filePath = filePath;
        this.fileDownload = fileDownload;
        this.score = score;
    }
}
