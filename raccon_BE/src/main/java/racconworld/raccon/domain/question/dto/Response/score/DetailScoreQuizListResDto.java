package racconworld.raccon.domain.question.dto.Response.score;


import lombok.*;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.entity.TestType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class DetailScoreQuizListResDto {

    private String testName;
    private TestType testType;
    private Long view;
    private String filePath;
    private List<DetailScoreQuestionListResDto> questions; // List<DetailQuestionListResDto> 타입으로 정의합니다.


    @Builder
    public DetailScoreQuizListResDto(String testName, TestType testType, Long view, String filePath, List<DetailScoreQuestionListResDto> questions) {
        this.testName = testName;
        this.testType = testType;
        this.view = view;
        this.filePath = filePath;
        this.questions = questions;
    }

    public static DetailScoreQuizListResDto toDto(Test test, List<Question> detailQuizList) {

        List<DetailScoreQuestionListResDto> questionDtos = detailQuizList.stream()
                .map(question -> DetailScoreQuestionListResDto.builder()
                .questionText(question.getQuestionText())
                .choices(question.getChoices().stream()
                        .map(choice -> DetailScoreChoiceListResDto.builder()
                                .choiceText(choice.getChoiceText())
                                .score((Long) choice.getScore()) // 여기서는 다운캐스팅 없이 getScore()를 호출
                                .build())
                        .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());

        return DetailScoreQuizListResDto.builder()
                .testName(test.getTestName())
                .testType(test.getTestType())
                .view(test.getView())
                .filePath(test.getFilePath())
                .questions(questionDtos)
                .build();

    }
}
