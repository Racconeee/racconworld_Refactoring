package racconworld.raccon.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import racconworld.raccon.domain.user.repository.UserRepository;
import racconworld.raccon.global.Security.handler.LoginFailureHandler;
import racconworld.raccon.global.Security.handler.LoginSuccessHandler;
import racconworld.raccon.global.Security.userdetails.CustomUserDetailsService;
import racconworld.raccon.global.jwt.CustomUsernamePasswordAuthenticationFilter;
import racconworld.raccon.global.jwt.JwtAuthenticationProcessingFilter;
import racconworld.raccon.global.jwt.service.JwtService;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] URL_WHITE_LIST = {
            "/login", "/favicon.ico",
            "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**",
            "/test/*" , "/quiz/*" , "/api/*/",

    };

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
                .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 JWT 토큰을 쿠키에 넣을지, LocalStorage에 넣을지에 따라 비활성화 여부 결정
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request  -> request
                                .requestMatchers(URL_WHITE_LIST).permitAll()
//                                .requestMatchers("*").permitAll() //Test 단계에서 일시적으로
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                                .requestMatchers(URL_WHITE_LIST).permitAll()
                                .anyRequest().authenticated())
                .addFilterAfter(customUsernamePasswordAuthenticationFilter(), LogoutFilter.class)
                .addFilterBefore(JwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public JwtAuthenticationProcessingFilter JwtAuthenticationProcessingFilter() {
        return new JwtAuthenticationProcessingFilter(jwtService, userRepository, bCryptPasswordEncoder(), URL_WHITE_LIST);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService , userRepository);
    }

    @Bean
    CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter
                = new CustomUsernamePasswordAuthenticationFilter();

        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(provider);
    }


    // CORS 설정
    CorsConfigurationSource corsConfigurationSource() {
        final List<String> allowedHeaders = List.of("*");
        final List<String> allowedOriginPatterns = List.of(
//                "http://localhost:9000",
//                "http://localhost:9001",
                "*"
        );
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(allowedHeaders);
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(allowedOriginPatterns); //  허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }
}