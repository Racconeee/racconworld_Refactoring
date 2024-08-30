package racconworld.raccon.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import racconworld.raccon.domain.user.service.AdminService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping("/test/delete")
    public ResponseEntity<BaseResponse<String>> deleteTest(@RequestParam Long testId){

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                adminService.deleteTest(testId)
        );


    }
}
