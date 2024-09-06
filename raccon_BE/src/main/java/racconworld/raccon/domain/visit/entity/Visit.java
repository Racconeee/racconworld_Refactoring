package racconworld.raccon.domain.visit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Visit {

    //@GeneratedValue 설정을 안함으로써 직접적인 값을 제공해야 함
    @Id
    @Column(name = "visit_id")
    private String id;
    private Long visitCount;


}
