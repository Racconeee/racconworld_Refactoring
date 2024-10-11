package racconworld.raccon.global.jwt.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

//JWT 기반 인증에서 효율적인 권한 처리 방법은 데이터베이스 접근을 최소화하고, JWT 토큰 자체에 필요한 모든 정보를 포함하여 인증 절차를 간소화하는 것입니다.

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {



    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.header}")
    private String accessHeader;
    @Value("${jwt.refresh.header}")
    private String refreshHeader;
    @Value("${jwt.access.expiration}")
    private Long accessExpiration;
    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERNAME_CLAIM = "username";
    private static final String BEARER = "Bearer ";

//
//    public Authentication authenticateAccessToken(HttpServletRequest request) {
//        String accessToken = extractAccessToken(request).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.MISSING_ACCESS_TOKEN));
//        Claims claims = verifyJwtToken(accessToken);
//
//        UserDetails userDetails = CustomUserDetails.builder()
//                .username((String) claims.get("username"))
//                .authorities(JwtClaimsParser.getROLE(claims))
//                .build();
//
//        return new UsernamePasswordAuthenticationToken(userDetails, null,
//                userDetails.getAuthorities());
//    }


    public String createAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {

        System.out.println(authorities);
        System.out.println(authorities);
        System.out.println(authorities);
        System.out.println(authorities);
        System.out.println(System.currentTimeMillis() + accessExpiration);
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpiration))
                .withClaim(USERNAME_CLAIM, username)
                .withArrayClaim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String createRefreshToken() {
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiration))
                .sign(Algorithm.HMAC256(secretKey));
    }

    //나는 헤더를 통해서 보낼거여서 이렇게 하고
    //만약 dto로 보내면 dto로 하면 되겟지 ?

    public void setAccessTokenHeader (HttpServletResponse response , String accessToken) {
        response.setHeader(accessHeader , accessToken);
    }

    public void setRefreshTokenHeader ( HttpServletResponse response , String refreshToken) {
        response.setHeader(refreshHeader , refreshToken);
    }

    //custom header라서 작성해줘야
    //FE에서 해당 토큰 값에 접근이 가능하다
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setHeader("Access-Control-Expose-Headers", "AccessToken, RefreshToken");
        response.setStatus(HttpServletResponse.SC_OK);
        log.info(" setAccessToken : {}"  ,accessToken);
        log.info(" setRefreshToken : {}"  ,refreshToken);

        setAccessTokenHeader(response,BEARER + accessToken);
        setRefreshTokenHeader(response,BEARER + refreshToken);
    }

    public Optional<String> extractUsername(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(accessToken)
                    .getClaim(USERNAME_CLAIM)
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            return true;
        }catch (TokenExpiredException e) {
            // 토큰이 만료된 경우 처리
            log.error("토큰이 만료되었습니다: {}", e.getMessage());
            throw new CustomExceptionHandler(ErrorCode.TOKEN_EXPIRED);
        } catch (JWTVerificationException e) {
            // 서명이 유효하지 않거나, 기타 검증 실패
            log.error("유효하지 않은 토큰입니다: {}", e.getMessage());
            throw new CustomExceptionHandler(ErrorCode.INVALID_TOKEN_SIGNATURE);
        }
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(AccessToken -> AccessToken.startsWith(BEARER))
                .map(AccessToken -> AccessToken.replace(BEARER, ""));
    }
    //위에는 해더에서 꺼내기 이거는 토큰 BEARER 짤라주기
    public String bodyExtractAccessToken(String token) {
            return token.replace(BEARER, "").trim();
    }

//
//    public boolean verifyJwtToken(String token) {
//
//        try {
//                Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//                return true;
//        } catch (Exception e) {
//            log.error("JWT Token 검증 실패: {}", e.getMessage());
//            throw new CustomExceptionHandler(ErrorCode.INVALID_ACCESS_TOKEN_SIGNATURE);
//        }
//    }

}