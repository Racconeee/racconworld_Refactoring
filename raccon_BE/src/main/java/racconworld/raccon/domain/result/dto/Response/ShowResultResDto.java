package racconworld.raccon.domain.result.dto.Response;

import lombok.Data;

@Data
public class ShowResultResDto {

    private String fileName;
    private String filePath;

    public ShowResultResDto(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
