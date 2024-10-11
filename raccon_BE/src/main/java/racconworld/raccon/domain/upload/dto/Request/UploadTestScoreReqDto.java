package racconworld.raccon.domain.upload.dto.Request;

import lombok.Builder;
import lombok.Data;
import racconworld.raccon.domain.test.entity.TestType;

import java.util.List;

@Data
public class UploadTestScoreReqDto {

    private String testName;
    private TestType testType; // 테스트 유형 (SCORE, PERSONALITY)
    private List<uploadQuestionsReqDto> questions;

    @Data
    @Builder
    public static class uploadQuestionsReqDto {

        private String questionText;
        private List<uploadChoicesScoreReqDto> choices;
    }

    @Data
    @Builder
    public static class uploadChoicesScoreReqDto {
        private String choiceText;
        private Long score;
    }



}
