package racconworld.raccon.global.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import racconworld.raccon.domain.user.entity.User;
import racconworld.raccon.domain.user.repository.UserRepository;
import racconworld.raccon.global.Security.userdetails.CustomUserDetails;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;
import racconworld.raccon.global.jwt.service.JwtService;

import java.io.IOException;
import java.util.Collection;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final String[] URL_WHITE_LIST;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    public JwtAuthenticationProcessingFilter(JwtService jwtService, UserRepository userRepository ,BCryptPasswordEncoder bCryptPasswordEncoder, String[] URL_WHITE_LIST) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.URL_WHITE_LIST = URL_WHITE_LIST;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info(" 토큰이 들어 왔습니다. 시작합니다");



        log.info("들어온 url : {} " , request.getRequestURI());
        if(PatternMatchUtils.simpleMatch(URL_WHITE_LIST ,request.getRequestURI())){
            log.info("통과합니다 url : {} " , request.getRequestURI());

            filterChain.doFilter(request ,response);
            return;
        }
        log.info("권한이 필요한 url : {} " , request.getRequestURI());
        log.info("request.getHeader(AccessToken) : {}" , request.getHeader("AccessToken"));
        log.info("request.getHeader(refreshHeader) : {}" , request.getHeader("RefreshToken"));

        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

        log.info("refreshToken : {}" , refreshToken);


        if(refreshToken != null){
            checkRefreshTokenAndReIssueAccessToken(response , refreshToken);
            return;
        }
        checkAccessTokenAndAuthentication(request, response, filterChain);
    }
    //refresh 토큰 보고 ac , rf 토큰 재발행
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        User userEntity = userRepository.findByRefreshToken(refreshToken).orElseThrow( () -> new CustomExceptionHandler(ErrorCode.TOKEN_NOT_FOUND));
        String reIssuedRefreshToken = reIssueRefreshToken(userEntity);
        jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(userEntity.getUsername()), reIssuedRefreshToken);
    }


    //rf 토큰 재발행
    private String reIssueRefreshToken(User user) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        user.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(user);
        return reIssuedRefreshToken;
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  FilterChain filterChain) throws ServletException, IOException {
        log.info("");
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresent(accessToken -> jwtService.extractUsername(accessToken)
                        .ifPresent(username -> userRepository.findByUsername(username)
                                .ifPresent(this::saveAuthentication)));

        log.info("검증 완료 이상 없음.");

        filterChain.doFilter(request, response);
    }

    public void saveAuthentication(User user) {


         CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .username(user.getUsername())
                 .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .authorities(getAuthorities(user))
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(customUserDetails, null,
                        authoritiesMapper.mapAuthorities(customUserDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList(user.getRole().stream().map(Enum::name).toList());
    }
}
