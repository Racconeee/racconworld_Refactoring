package racconworld.raccon.domain.question.dto.Response.personality;

import lombok.*;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class DetailPersonalityQuestionListResDto {
    private String questionText;
    private List<DetailPersonalityChoiceListResDto> choices; // List<ShowChoiceListResDto> 타입으로 정의합니다.

    @Builder
    public DetailPersonalityQuestionListResDto(String questionText, List<DetailPersonalityChoiceListResDto> choices) {
        this.questionText = questionText;
        this.choices = choices;
    }
}
