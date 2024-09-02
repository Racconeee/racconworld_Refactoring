package racconworld.raccon.global.jwt;

import org.springframework.security.core.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import racconworld.raccon.global.common.ErrorResponse;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.jwt.service.JwtService;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Arrays;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final String[] URL_WHITE_LIST;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //나중에 PatternMatchUtils 꼭 정리하자 신기하다
        //.equals 를 안해도 된다
        if(PatternMatchUtils.simpleMatch(URL_WHITE_LIST ,request.getRequestURI())){
          filterChain.doFilter(request ,response);
          return;
        }


        try {
            log.info("토큰을 검증합니다.");
            Authentication authentication = jwtService.authenticateAccessToken(request);

            log.info("토큰이 검증되었습니다. SecurityContextHolder에 저장합니다.");
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.info("액세스 토큰이 만료되었습니다.");
            sendJwtErrorRes(ErrorCode.EXPIRED_ACCESS_TOKEN_EXCEPTION, response);
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.info("액세스 토큰이 유효하지 않습니다.");
            sendJwtErrorRes(ErrorCode.INVALID_ACCESS_TOKEN_EXCEPTION, response);
        } catch (IllegalArgumentException e) {
            log.info("액세스 토큰이 존재하지 않습니다.");
            sendJwtErrorRes(ErrorCode.EXISTENCE_TOKEN_EXCEPTION, response);
        }
    }

    // 직렬화 과정
    public void sendJwtErrorRes(ErrorCode errorCode, HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getStatus());
        objectMapper.writeValue(response.getWriter(), ErrorResponse.of().code(errorCode).build());
    }
}
