package racconworld.raccon.domain.user.controller;


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
    @PostMapping("/token")
    public ResponseEntity<BaseResponse<String>> validateAccessToken(@RequestBody AccessTokenReqDto accessTokenReqDto ) {

        log.info("token_value :");
        log.info("검증에 들어온 token_value :{} " , accessTokenReqDto.getAccessToken());

        String accessToken = jwtService.bodyExtractAccessToken(accessTokenReqDto.getAccessToken());
        jwtService.isTokenValid(accessToken);
        return BaseResponse.success(SuccessCode.VALID_ACCESS_TOKEN_SIGNATURE , "인증이 완료 되었습니다.");
    }
//
//    @PostMapping("/refreshtoken")
//    public ResponseEntity<BaseResponse<String>> validateRefreshToken(@RequestHeader String  refreshToken) {
//
//        log.info("RFtoken_value :");
//        log.info("검증에 들어온 RFtoken_value :{} " , refreshTokenReqDto.getRefreshToken());
//
//        String refreshToken = jwtService.bodyExtractAccessToken(refreshTokenReqDto.getRefreshToken());
//        boolean TokenValidResult = jwtService.isTokenValid(refreshToken);
//        if(TokenValidResult) {
//            return BaseResponse.success(SuccessCode.VALID_ACCESS_TOKEN_SIGNATURE , "인증이 완료 되었습니다.");
//        } else {
//            return BaseResponse.error(ErrorCode.INVALID_ACCESS_TOKEN_SIGNATURE ,"토큰 값이 유효하지 않습니다.");
//        }
//    }
//
//    @PostMapping("/token")
//    public ResponseEntity<BaseResponse<String>> reissueRefreshToken(@RequestBody RefreshTokenReqDto refreshTokenReqDto ) {
//
//        log.info("RefreshToken :");
//        log.info("검증에 들어온 RefreshToken :{} " , refreshTokenReqDto.getRefreshToken());
//
//        String accessToken = jwtService.bodyExtractAccessToken(refreshTokenReqDto.getRefreshToken());
//        boolean TokenValidResult = jwtService.isTokenValid(accessToken);
//        if(TokenValidResult) {
//            return BaseResponse.success(SuccessCode.VALID_ACCESS_TOKEN_SIGNATURE , "인증이 완료 되었습니다.");
//        } else {
//            return BaseResponse.error(ErrorCode.INVALID_ACCESS_TOKEN_SIGNATURE ,"토큰 값이 유효하지 않습니다.");
//        }
//    }
//

    @GetMapping("/a")
    public String validateAccessToken() {

        log.info("a 호출 ADMIN");
        return "admin 권한 획득";
    }

}
