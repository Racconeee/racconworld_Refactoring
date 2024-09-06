package racconworld.raccon.domain.question.dto.Response.personality;

import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
public class DetailPersonalityChoiceListResDto {

    private String choiceText;
    private String score;

    @Builder
    public DetailPersonalityChoiceListResDto(String choiceText, String score) {
        this.choiceText = choiceText;
        this.score = score;
    }
}
