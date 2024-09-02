package racconworld.raccon.global.Security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import racconworld.raccon.domain.user.repository.UserRepository;
import racconworld.raccon.global.jwt.service.JwtService;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {


    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Value("${redirectUrl.login.success}")
    private String REDIRECT_URI_SUCCESS;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("로그인 성공 ");


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String accessToken = jwtService.createAccessToken(username);
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response , accessToken, refreshToken);

        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    user.updateRefreshToken(refreshToken);
                    userRepository.saveAndFlush(user);
                });


        response.sendRedirect(REDIRECT_URI_SUCCESS);
    }
}
