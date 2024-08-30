package racconworld.raccon.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements  AdminService{

    private final TestRepository testRepository;



    public String deleteTest(Long testId){

//        testRepository.findById(testId).orElseThrow( () -> new CustomExceptionHandler(ErrorCode.BAD_REQUEST , "해당하는 TestID가 존재하지않습니다."));

        testRepository.deleteById(testId);

        return "Test 삭제 성공!";
    }
}
