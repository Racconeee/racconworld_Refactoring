package racconworld.raccon.domain.question.dto.Response.score;

import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
public class DetailScoreChoiceListResDto {

    private String choiceText;
    private Long score;

    @Builder
    public DetailScoreChoiceListResDto(Long score, String choiceText) {
        this.score = score;
        this.choiceText = choiceText;
    }
}