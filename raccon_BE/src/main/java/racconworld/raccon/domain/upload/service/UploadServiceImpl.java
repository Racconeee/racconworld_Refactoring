package racconworld.raccon.domain.upload.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import racconworld.raccon.domain.choice.entity.sub.PersonalityChoice;
import racconworld.raccon.domain.choice.entity.sub.ScoreChoice;
import racconworld.raccon.domain.choice.repository.ChoiceRepository;
import racconworld.raccon.domain.question.entity.Question;
import racconworld.raccon.domain.question.repository.QuestionRepository;
import racconworld.raccon.domain.result.entity.Result;
import racconworld.raccon.domain.result.repository.ResultRepository;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.entity.TestType;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.upload.dto.Request.UploadTestPersonalityReqDto;
import racconworld.raccon.domain.upload.dto.Request.UploadTestScoreReqDto;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final ResultRepository resultRepository;


    @Value("${imagePath.test.dir}")
    private String testFileDir;
    @Value("${imagePath.result.dir}")
    private String resultFileDir;

    //score , personality 다 같고 마지막만 다르게 하면 됨

    @Override
    @Transactional
    public String uploadTestTypeScore(UploadTestScoreReqDto dto, MultipartFile testImage, List<MultipartFile> resultImages) throws IOException {

        Test testEntity = createTest(dto.getTestName() , dto.getTestType() ,testImage);

        for ( UploadTestScoreReqDto.uploadQuestionsReqDto question : dto.getQuestions()) {

            Question questionEntity = new Question(testEntity , question.getQuestionText());
            questionRepository.save(questionEntity);

            for (UploadTestScoreReqDto.uploadChoicesScoreReqDto choice : question.getChoices()) {

                ScoreChoice scoreChoiceEntity = new ScoreChoice(questionEntity , choice.getChoiceText() ,choice.getScore());
                choiceRepository.save(scoreChoiceEntity);
            }
        }
        uploadResult( resultImages, testEntity);

        return dto.getTestType() + "의 타입의 테스트가 정상적으로 저장 되었습니다.";

    }


    //성격 유형은 score로 안하고 타입이 달라서 personality로 했었는데 그렇게 되면
    //FE에서 코드를 많이 짜야함 그냥 프론트에서 dto 통일(score) 시켜서 받아서 처리하자 줄떄는 생성자로 하면 personality로 알아서 잘 들어감
    @Override
    @Transactional
    public String uploadTestTypePersonality(UploadTestPersonalityReqDto dto, MultipartFile testImage, List<MultipartFile> resultImages) throws IOException {

        Test testEntity = createTest(dto.getTestName() , dto.getTestType() ,testImage);

        for ( UploadTestPersonalityReqDto.uploadQuestionsReqDto question : dto.getQuestions()) {
            Question questionEntity = new Question(testEntity , question.getQuestionText());
            questionRepository.save(questionEntity);

            for (UploadTestPersonalityReqDto.uploadChoicesPersonalityReqDto choice : question.getChoices()) {
                PersonalityChoice personalityChoice = new PersonalityChoice(questionEntity , choice.getChoiceText() ,choice.getScore());
                choiceRepository.save(personalityChoice);
            }
        }
        uploadResult( resultImages, testEntity);

        return dto.getTestType() + "의 타입의 테스트가 정상적으로 저장 되었습니다.";

    }


    @Override
    public Test createTest(String TestName , TestType TestType , MultipartFile testImage) throws IOException {


        testRepository.findByTestName(TestName).ifPresent(test -> {
            throw new CustomExceptionHandler(ErrorCode.EXIST_SAME_TEST, "같은 이름의 테스트가 존재합니다.");
        });


//                ifPresent(test -> {
//            throw new CustomExceptionHandler(ErrorCode.NOT_FOUND, "같은 이름의 테스트가 존재합니다.");
//        });

        Test testEntity = testRepository.save(new Test(TestName , 0L , TestType ));
        String filepath = testFileDir + testEntity.getId() + "/main";
        testEntity.uploadFilePath(filepath);

        createDir(filepath);
        saveFile(testImage,"main.png", filepath);
        //파일 까지 생성하고 저장하기
        testRepository.save(testEntity);

        return testEntity;
    }


    // 저장 방식
    // filepath : spring/img
    // filename : 1
    // filepath + filename 형식으로 저장된다 -> spring/img/1
    @Override
    public void saveFile(MultipartFile testImage, String filename, String filepath) throws IOException {
        File saveFile = new File(filepath, filename);
        testImage.transferTo(saveFile);
    }


    //filepath에 해당하는 파일이 없다면 파일을 생성해준다.
    @Override
    public void createDir(String filepath) throws IOException {
        Path imagePath = Paths.get(filepath);
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
        }
    }
    // result
    /*
     * result 결과 이미지 등록 메소드
     * 예외처리
     * 1.이미 이미지가 존재하는 경우
     * 2.Test가 존재하지 않는경우
     * */

    public void uploadResult(List<MultipartFile> fileList, Test testEntity) throws IOException {

        for (MultipartFile file : fileList) {
            String score = file.getOriginalFilename();
            String filePath = resultFileDir + testEntity.getId();
            String fileName = testEntity.getId() + "/" + score;

//            createDir(filePath); Test 사진 넣으면 서 이미 넣었으니 우선 주석으로 테스트 해보자
            saveFile(file, score, filePath);

            resultRepository.save(new Result(testEntity ,fileName ,filePath, score ));

            //문제가 없다면 파일 생성
        }
    }

}
