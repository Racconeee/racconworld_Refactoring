package racconworld.raccon.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.user.service.AdminService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    //다른 조건이 없기에 이렇게 설계햇다.
    //만약 다른 조건이 추가된다면 dto나 RequestParam을 채택 햇을 듯

    @Operation(summary = "TEST 삭제",
            description =  "testId 에 대한 테스트 삭제 관련된 데이터 모두 삭제")
    @DeleteMapping("/test/delete/{testId}")
    public ResponseEntity<BaseResponse<String>> deleteTest(@PathVariable Long testId){
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                adminService.deleteTest(testId)
        );
    }

}
