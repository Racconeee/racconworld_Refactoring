package racconworld.raccon.domain.upload.controller;


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
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {


    private final UploadService uploadService;

    //데이터 dto  , 사진들 따로 받아서 처리

    @PostMapping("/admin/score")
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

    @PostMapping("/admin/personality")
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
