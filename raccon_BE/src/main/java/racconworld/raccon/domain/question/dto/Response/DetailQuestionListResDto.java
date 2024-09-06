package racconworld.raccon.domain.question.dto.Response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class DetailQuestionListResDto {
    private String questionText;
    private List<DetailChoiceListResDto> choices; // List<ShowChoiceListResDto> 타입으로 정의합니다.

    @Builder
    public DetailQuestionListResDto(String questionText, List<DetailChoiceListResDto> choices) {
        this.questionText = questionText;
        this.choices = choices;
    }
}
