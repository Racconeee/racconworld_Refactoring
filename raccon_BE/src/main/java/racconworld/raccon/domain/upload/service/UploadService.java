package racconworld.raccon.domain.upload.service;


import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.entity.TestType;
import racconworld.raccon.domain.upload.dto.Request.UploadTestPersonalityReqDto;
import racconworld.raccon.domain.upload.dto.Request.UploadTestScoreReqDto;

import java.io.IOException;
import java.util.List;

public interface UploadService {

    String uploadTestTypeScore(UploadTestScoreReqDto dto, MultipartFile testImage, List<MultipartFile> resultImages) throws IOException;
    String uploadTestTypePersonality(UploadTestPersonalityReqDto dto, MultipartFile testImage, List<MultipartFile> resultImages) throws IOException;

    Test createTest(String TestName , TestType TestType , MultipartFile testImage) throws IOException;
    void saveFile(MultipartFile testImage, String filename, String filepath) throws IOException;
    void createDir(String filepath) throws IOException;
    void uploadResult(List<MultipartFile> fileList, Test testEntity) throws IOException;

}
