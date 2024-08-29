package racconworld.raccon.domain.question.dto.Response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
public class DetailPersonalQuizRes {

    private String testName;
    private String testType;
    private String filePath;
    private String fileDownload;
    private List<QuestionDto> questions; // List<QuestionDto> 타입으로 정의합니다.
    @Getter
    @Setter
    @NoArgsConstructor
    public static class QuestionDto {
        private String questionText;
        private List<ChoiceDto> choices; // List<ChoiceDto> 타입으로 정의합니다.
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ChoiceDto {
        private String choiceText;
        private String personality; // 숫자는 Integer로 정의합니다.
    }
}
