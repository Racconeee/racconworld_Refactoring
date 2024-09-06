package racconworld.raccon.domain.log.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "log")

public class Log {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long Id;

    private String requestUrl;

    private String logUuid;

    @Column
    private LocalDateTime viewTimestamp;


    @PrePersist
    public void prepersist() {
        this.viewTimestamp = LocalDateTime.now();
    }


    @Builder
    public Log(String requestUrl, String logUuid, LocalDateTime viewTimestamp) {
        this.requestUrl = requestUrl;
        this.logUuid = logUuid;
        this.viewTimestamp = viewTimestamp;
    }
}
