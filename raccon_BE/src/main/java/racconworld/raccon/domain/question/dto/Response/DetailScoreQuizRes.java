package racconworld.raccon.domain.question.dto.Response;


import lombok.*;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.entity.TestType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Builder
public class DetailScoreQuizRes {

    private String testName;
    private TestType testType;
    private String filePath;
    private String fileDownload;
    private List<QuestionDto> questions; // List<QuestionDto> 타입으로 정의합니다.

    @Getter
    @Setter
    @Builder
    public static class QuestionDto {
        private String questionText;
        private List<ChoiceDto> choices; // List<ChoiceDto> 타입으로 정의합니다.
    }

    @Getter
    @Setter
    @Builder
    public static class ChoiceDto {
        private String choiceText;
        private Long score; // 숫자는 Integer로 정의합니다.
    }

    public static DetailScoreQuizRes toDto(Test test , List<Question> DetailQuizList) {
        return DetailScoreQuizRes.builder()
                .testName(test.getTestName())
                .testType(test.getTestType())
                .filePath(test.getFilePath())
                .fileDownload(test.getFileDownload())
                .questions(DetailQuizList.stream()
                        .map(question -> QuestionDto.builder()
                                .questionText(question.getQuestionText())
                                .choices( choice -> ChoiceDto.builder()
                                        .choiceText(choice.getChoiceText())
                                        .score(choice.getScore())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()));
    }



}
