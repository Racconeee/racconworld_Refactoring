package racconworld.raccon.domain.question.dto.Response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class DetailChoiceListResDto<T> {

    private String choiceText;
    private T score;

    @Builder
    public DetailChoiceListResDto(String choiceText, T score) {
        this.choiceText = choiceText;
        this.score = score;
    }
}
