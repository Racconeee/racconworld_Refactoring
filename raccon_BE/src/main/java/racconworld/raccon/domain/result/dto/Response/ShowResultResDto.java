package racconworld.raccon.domain.result.dto.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ShowResultResDto {

    private String filePath;

    public ShowResultResDto(String filePath) {
        this.filePath = filePath;
    }
}
