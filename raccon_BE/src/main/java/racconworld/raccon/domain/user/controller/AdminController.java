package racconworld.raccon.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.user.service.AdminService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    //다른 조건이 없기에 이렇게 설계햇다.
    //만약 다른 조건이 추가된다면 dto나 RequestParam을 채택 햇을 듯
    @DeleteMapping("/test/delete/{testId}")
    public ResponseEntity<BaseResponse<String>> deleteTest(@PathVariable Long testId){

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                adminService.deleteTest(testId)
        );


    }
}
