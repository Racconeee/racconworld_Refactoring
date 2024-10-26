package racconworld.raccon.domain.test.dto.Response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TestTotalVisitResDto {

    private Long TestTotalVisit;

    public TestTotalVisitResDto(Long testTotalVisit) {
        TestTotalVisit = testTotalVisit;
    }
}
