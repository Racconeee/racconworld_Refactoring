package racconworld.raccon.domain.result;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racconworld.raccon.domain.test.Test;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "test_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Test result_to_test;

    @Column
    private String filePath;
    @Column
    private String fileDownload;
    @Column
    private String score;


}
