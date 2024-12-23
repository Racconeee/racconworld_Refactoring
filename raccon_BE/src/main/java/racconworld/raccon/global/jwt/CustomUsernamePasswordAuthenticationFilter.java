package racconworld.raccon.global.jwt;


import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    //Security 기본 경로가 /login 인데 변경 안하고 그대로 사용함
    private static final String DEFAULT_LOGIN_REQUEST_URL = "/api/login"; // "/login"으로 오는 요청을 처리
    private static final String HTTP_METHOD = "POST"; // 로그인 HTTP 메소드는 POST
    private static final String CONTENT_TYPE = "application/json"; // JSON 타입의 데이터로 오는 로그인 요청만 처리
    private static final String USERNAME_KEY = "username"; // 회원 로그인 시 이메일 요청 JSON Key : "username"
    private static final String PASSWORD_KEY = "password"; // 회원 로그인 시 비밀번호 요청 JSon Key : "password"
    private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD);


    //DEFAULT_LOGIN_REQUEST_URL 경로에 대해서 작동함
    public CustomUsernamePasswordAuthenticationFilter() {
        super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)){
            throw new CustomExceptionHandler(ErrorCode.BAD_REQUEST ,"요청은 "+CONTENT_TYPE+ "형식으로 요청해야 합니다. 현재 타입은 "+request.getContentType());
        }



        ObjectMapper objectMapper = new ObjectMapper();

        String messageBody = StreamUtils.copyToString(request.getInputStream() , StandardCharsets.UTF_8);
        log.info("messageBody : {}" , messageBody);
        //제네릭 타입 명시해야함 안하면 오류 발생
//        Note: D:\0920\racconworld_Refactoring\raccon_BE\src\main\java\racconworld\raccon\global\jwt\CustomUsernamePasswordAuthenticationFilter.java uses unchecked or unsafe operations.
//        Note: Recompile with -Xlint:unchecked for details.
//        Map<String , String> usernamePasswordMap = objectMapper.readValue(messageBody , Map.class);
        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, new TypeReference<Map<String, String>>() {});

        log.info("usernamePasswordMap : {}" ,usernamePasswordMap);
        String username = usernamePasswordMap.get(USERNAME_KEY);
        String password = usernamePasswordMap.get(PASSWORD_KEY);


        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);

        log.info("UsernamePasswordAuthenticationToken : {}" ,token);

        Authentication authentication = getAuthenticationManager().authenticate(token);
        log.info("authentication : {}" ,authentication);

        return authentication;


    }


}