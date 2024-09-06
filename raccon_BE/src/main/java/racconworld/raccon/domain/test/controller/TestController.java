package racconworld.raccon.domain.test.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.test.service.TestService;
import racconworld.raccon.domain.upload.dto.Response.TestTotalVisitResDto;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TestController {

    private final TestService testService;


    //필터링과 같은 건 RequestParam이 조건 전달하기나 복수의 조건 전달할떄는 명료하게 볼 수 잇어서 채택
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShowTestResDto>> getTestList(@RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber) {

        ShowTestResDto testList = testService.getTestListByPage(pageNumber);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                testList
        );
    }


    @GetMapping("/total/visit")
    public ResponseEntity<BaseResponse<TestTotalVisitResDto>> getTestTotalVisit() {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                testService.getTestTotalVisit()
        );
    }
}
