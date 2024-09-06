package racconworld.raccon.domain.question.dto.Response.personality;

import lombok.*;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.entity.TestType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@RequiredArgsConstructor
public class DetailPersonalityQuizResDto {

    private String testName;
    private TestType testType;
    private Long view;
    private String filePath;
    private String fileName;
    private List<DetailPersonalityQuestionListResDto> questions; // List<DetailQuestionListResDto> 타입으로 정의합니다.

    @Builder
    public DetailPersonalityQuizResDto(String testName, TestType testType, Long view, String filePath, String fileName, List<DetailPersonalityQuestionListResDto> questions) {
        this.testName = testName;
        this.testType = testType;
        this.view = view;
        this.filePath = filePath;
        this.fileName = fileName;
        this.questions = questions;
    }

    public static DetailPersonalityQuizResDto toDto(Test test, List<Question> detailQuizList) {


        List<DetailPersonalityQuestionListResDto> questionDtos = detailQuizList.stream()
                .map(question -> DetailPersonalityQuestionListResDto.builder()
                        .questionText(question.getQuestionText())
                        .choices(question.getChoices().stream()
                                .map(choice -> DetailPersonalityChoiceListResDto.builder()
                                        .choiceText(choice.getChoiceText())
                                        .score((String) choice.getScore()) // 여기서는 다운캐스팅 없이 getScore()를 호출
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return DetailPersonalityQuizResDto.builder()
                .testName(test.getTestName())
                .testType(test.getTestType())
                .view(test.getView())
                .filePath(test.getFilePath())
                .fileName(test.getFileName())
                .questions(questionDtos)
                .build();

    }
}
