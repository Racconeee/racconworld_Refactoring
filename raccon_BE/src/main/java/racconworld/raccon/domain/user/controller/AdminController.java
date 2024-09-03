package racconworld.raccon.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.user.service.AdminService;
import racconworld.raccon.domain.user.service.UserService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;
import racconworld.raccon.global.jwt.dto.Request.IdPwTokenReqDto;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    //다른 조건이 없기에 이렇게 설계햇다.
    //만약 다른 조건이 추가된다면 dto나 RequestParam을 채택 햇을 듯
    @DeleteMapping("/test/delete/{testId}")
    public ResponseEntity<BaseResponse<String>> deleteTest(@PathVariable Long testId){
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                adminService.deleteTest(testId)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> createAdmin(@RequestBody IdPwTokenReqDto idPwTokenReqDto) {

        log.info("username : {} " , idPwTokenReqDto.getUsername());
        log.info("password : {} " , idPwTokenReqDto.getPassword());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                userService.signUpAdmin(idPwTokenReqDto.getUsername(), idPwTokenReqDto.getPassword())
        );

    }
}
