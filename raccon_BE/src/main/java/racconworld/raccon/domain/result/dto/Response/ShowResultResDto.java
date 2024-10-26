package racconworld.raccon.domain.result.dto.Response;

import lombok.Data;

@Data
public class ShowResultResDto {

    private String filePath;

    public ShowResultResDto(String filePath) {
        this.filePath = filePath;
    }
}
