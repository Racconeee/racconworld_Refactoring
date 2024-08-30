package racconworld.raccon.domain.test.dto.Response;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class showTestResDto {



    private boolean hasNext;
    private List<showTestListDto> showTestListDtos;

    @Data
    public static class showTestListDto {
        private Long testId;
        private String testName;
        private String view;
        private String fileName;
        private String filePath;

        public showTestListDto(Long testId, String testName, String view,  String fileName, String filePath) {
            this.testId = testId;
            this.testName = testName;
            this.view = view;
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }

    public showTestResDto(boolean hasNext, List<showTestListDto> showTestListDtos) {
        this.hasNext = hasNext;
        this.showTestListDtos = showTestListDtos;
    }
}
