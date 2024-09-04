package racconworld.raccon.global.Security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import racconworld.raccon.global.common.code.ErrorCode;

import java.io.IOException;


@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


        log.info("Login failed: {}", exception.getMessage());

        // 예를 들어 로그인 실패시 INVALID_USER_CREDENTIALS 코드를 사용하여 로그를 남기고 클라이언트에게 응답합니다.
        ErrorCode errorCode = ErrorCode.INVALID_USER_CREDENTIALS;

        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"errorCode\": \"%s\", \"message\": \"%s\"}", errorCode.getErrorCode(), errorCode.getMessage()));
    }

}
