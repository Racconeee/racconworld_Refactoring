package racconworld.raccon.domain.upload.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import racconworld.raccon.domain.upload.dto.Request.UploadTestPersonalityReqDto;
import racconworld.raccon.domain.upload.dto.Request.UploadTestScoreReqDto;
import racconworld.raccon.domain.upload.service.UploadService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    //데이터 dto  , 사진들 따로 받아서 처리


    @Operation(summary = "TEST CREATE ::SCORE::",
            description =  "SCORE 타입으로 TEST 생성" +
                            " 메인 img => 1장 결과 img =>  12장 문제 =>  10개 넣어야함 점수는 0~100 10점단위 점수 == 이미지이름 같게해야함 주의"
    )
    @PostMapping("/upload/score")
    public ResponseEntity<BaseResponse<String>> uploadTestTypeScore( @RequestPart UploadTestScoreReqDto uploadTestScoreReqDto,
                                                                     @RequestPart MultipartFile testImage,
                                                                     @RequestPart List<MultipartFile> resultImages) throws IOException {

        log.info("uploadTestScoreReqDto : {} ", uploadTestScoreReqDto);
        log.info("uploadTestScoreReqDto : {} ", testImage);
        log.info("uploadTestScoreReqDto : {} ", resultImages);

        return BaseResponse.success(SuccessCode.UPLOAD_SUCCESS ,
                uploadService.uploadTestTypeScore(uploadTestScoreReqDto, testImage, resultImages)
        );

    }


    @Operation(summary = "TEST CREATE ::PERSONALITY::",
            description =  "PERSONALITY 타입으로 TEST 생성" +
                    " 메인 img => 1장 결과 img =>  12장 문제 =>  10개 넣어야함 점수는 FE QuizCommon.JS 파일 참조 점수 == 이미지이름 같게해야함 주의"
    )
    @PostMapping("/upload/personality")
    public ResponseEntity<BaseResponse<String>> uploadTestTypePersonality( @RequestPart UploadTestPersonalityReqDto uploadTestPersonalityReqDto,
                                                                     @RequestPart MultipartFile testImage,
                                                                     @RequestPart List<MultipartFile> resultImages) throws IOException {


        log.info("uploadTestPersonalityReqDto : {} ", uploadTestPersonalityReqDto);
        log.info("testImage : {} ", testImage.getOriginalFilename());
        log.info("resultImage : {} ", resultImages.stream().map(MultipartFile::getOriginalFilename).toList());


        return BaseResponse.success(SuccessCode.UPLOAD_SUCCESS ,
                uploadService.uploadTestTypePersonality(uploadTestPersonalityReqDto, testImage, resultImages)
                );

    }
}
