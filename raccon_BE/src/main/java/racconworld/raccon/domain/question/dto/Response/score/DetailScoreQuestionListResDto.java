package racconworld.raccon.domain.question.dto.Response.score;

import lombok.*;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class DetailScoreQuestionListResDto {
    private String questionText;
    private List<DetailScoreChoiceListResDto> choices; // List<ShowChoiceListResDto> 타입으로 정의합니다.

    @Builder
    public DetailScoreQuestionListResDto(String questionText, List<DetailScoreChoiceListResDto> choices) {
        this.questionText = questionText;
        this.choices = choices;
    }
}