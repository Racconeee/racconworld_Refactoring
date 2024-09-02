package racconworld.raccon.global.Security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import racconworld.raccon.global.Security.userdetails.CustomUserDetailsService;
import racconworld.raccon.global.jwt.service.JwtService;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class FailureHandler implements AuthenticationFailureHandler {


    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("로그인 실패 ");

    }
}
