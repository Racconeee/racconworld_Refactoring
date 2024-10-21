package racconworld.raccon.domain.test.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;
import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.test.service.TestService;
import racconworld.raccon.domain.test.dto.Response.TestTotalVisitResDto;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;


    //필터링과 같은 건 RequestParam이 조건 전달하기나 복수의 조건 전달할떄는 명료하게 볼 수 잇어서 채택
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShowTestResDto>> getTestList(@RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber) {


        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                testService.getTestListByPage(pageNumber)
        );
    }

    //testId가 DB에서는 Long 타입일 수 있지만, Redis에서의 키는 **문자열(String)**로 처리됩니다
    @GetMapping("/list/view")
    public ResponseEntity<BaseResponse<List<ShowTestViewListResDto>>> getViewCounts(@RequestParam("testIds") List<Long> testIds) {
        // TestService를 호출하여 Redis 조회수 정보를 가져옴

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                testService.getTestViewList(testIds)
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
