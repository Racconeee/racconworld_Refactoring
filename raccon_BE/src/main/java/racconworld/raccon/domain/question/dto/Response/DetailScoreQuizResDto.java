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
public class DetailScoreQuizResDto {

    private String testName;
    private TestType testType;
    private String view;
    private String filePath;
    private String fileName;
    private List<DetailQuestionListResDto> questions; // List<DetailQuestionListResDto> 타입으로 정의합니다.

    @Getter
    @Setter
    @Builder
    public static class DetailQuestionListResDto {
        private String questionText;
        private List<ShowChoiceListResDto> choices; // List<ShowChoiceListResDto> 타입으로 정의합니다.
    }

    @Getter
    @Setter
    @Builder
    public static class ShowChoiceListResDto {
        private String choiceText;
        private Long score;
    }

    public static DetailScoreQuizResDto toDto(Test test, List<Question> detailQuizList) {


        List<DetailScoreQuizResDto.DetailQuestionListResDto> questionDtos = detailQuizList.stream()
                .map(question -> DetailScoreQuizResDto.DetailQuestionListResDto.builder()
                .questionText(question.getQuestionText())
                .choices(question.getChoices().stream()
                        .map(choice -> DetailScoreQuizResDto.ShowChoiceListResDto.builder()
                                .choiceText(choice.getChoiceText())
                                .score((Long) choice.getScore()) // 여기서는 다운캐스팅 없이 getScore()를 호출
                                .build())
                        .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());

        return DetailScoreQuizResDto.builder()
                .testName(test.getTestName())
                .testType(test.getTestType())
                .view(test.getView())
                .fileName(test.getFileName())
                .filePath(test.getFilePath())
                .questions(questionDtos)
                .build();

    }
}
