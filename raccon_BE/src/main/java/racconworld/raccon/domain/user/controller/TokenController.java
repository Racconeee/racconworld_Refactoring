package racconworld.raccon.domain.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.user.dto.Request.AccessTokenReqDto;
import racconworld.raccon.domain.user.dto.Request.RefreshTokenReqDto;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.common.code.SuccessCode;
import racconworld.raccon.global.jwt.service.JwtService;

import java.util.Optional;

@RestController
@RequestMapping("/api/validate")
@RequiredArgsConstructor
@Slf4j
public class TokenController {

    private final JwtService jwtService;


    //refresh 토큰을 보내면 무조건 재발급이니 -> 그래도 하나 만들어야됨
    //AccessToken 만 검증하면 된다.
    // 일관성 있게 헤더를 통해서 넘겨도 되지만
    //헤더로 넘기게 되면 필터에서 걸림 그래서 응답코드 ?도 만들어야함
    //그래서 차라리 RequestBody 로 넘기자.

    @Operation(summary = "ACCESSTOKEN 검증",
            description =  "엑세스 토큰 검증")
    @PostMapping("/token")
    public ResponseEntity<BaseResponse<String>> validateAccessToken(@RequestBody AccessTokenReqDto accessTokenReqDto ) {


        String accessToken = jwtService.bodyExtractAccessToken(accessTokenReqDto.getAccessToken());
        jwtService.isTokenValid(accessToken);
        return BaseResponse.success(SuccessCode.VALID_ACCESS_TOKEN_SIGNATURE , "인증이 완료 되었습니다.");
    }

}
