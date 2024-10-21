package racconworld.raccon.domain.test.dto.Response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ShowTestListViewResDto {

    private Long testId;
    private Long view;


    public ShowTestListViewResDto(Long testId, Long view) {
        this.testId = testId;
        this.view = view;
    }


}
