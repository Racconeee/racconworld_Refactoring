package racconworld.raccon.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.user.service.AdminService;
import racconworld.raccon.domain.user.service.UserService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;
import racconworld.raccon.domain.user.dto.Request.IdPwTokenReqDto;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

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


    @Operation(summary = "ADMIN 계정 회원가입",
            description =  "배포할 때는 삭제예정 - 회원가입 필요 없음 TEST를 위해 생성 API ")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> createAdmin(@RequestBody IdPwTokenReqDto idPwTokenReqDto) {

        log.info("username : {} " , idPwTokenReqDto.getUsername());
        log.info("password : {} " , idPwTokenReqDto.getPassword());
        return BaseResponse.success(
                SuccessCode.SIGNUP_SUCCESS,
                userService.signUpAdmin(idPwTokenReqDto.getUsername(), idPwTokenReqDto.getPassword())
        );

    }
}
