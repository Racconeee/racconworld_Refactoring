package racconworld.raccon.domain.upload.dto.Request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import racconworld.raccon.domain.test.entity.TestType;

import java.util.List;

@Data
public class UploadTestPersonalityReqDto {
    private String testName;
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)
    private List<uploadQuestionsReqDto> questions;

    @Data
    @Builder
    public static class uploadQuestionsReqDto {

        private String questionText;
        private List<uploadChoicesPersonalityReqDto> choices;
    }

    @Data
    @Builder
    public static class uploadChoicesPersonalityReqDto {
        private String choiceText;
        private String personality;
    }



}
