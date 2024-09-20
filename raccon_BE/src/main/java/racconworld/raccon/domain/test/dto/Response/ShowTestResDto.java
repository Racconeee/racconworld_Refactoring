package racconworld.raccon.domain.test.dto.Response;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class ShowTestResDto {



    private boolean hasNext;
    private List<ShowTestListDto> showTestListDtos;

    @Data
    @RequiredArgsConstructor
    public static class ShowTestListDto {
        private Long testId;
        private String testName;
        private Long view;
        private String fileName;
        private String filePath;

        public ShowTestListDto(Long testId, String testName, Long view, String fileName, String filePath) {
            this.testId = testId;
            this.testName = testName;
            this.view = view;
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }

    public ShowTestResDto(boolean hasNext, List<ShowTestListDto> showTestListDtos) {
        this.hasNext = hasNext;
        this.showTestListDtos = showTestListDtos;
    }
}
