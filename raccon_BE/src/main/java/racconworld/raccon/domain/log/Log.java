package racconworld.raccon.domain.log;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Log {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long Id;

    private String requestUrl;

    private String logUuid;

    @Column
    private LocalDateTime startTimestamp;

    @Column
    private LocalDateTime completeTimestamp;

    @Column
    private LocalDateTime viewTimestamp;


    @PrePersist
    public void prepersist() {
        this.viewTimestamp = LocalDateTime.now();
    }



}
