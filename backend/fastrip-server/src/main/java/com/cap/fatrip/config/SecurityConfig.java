package com.cap.fatrip.config;

import com.cap.fatrip.auth.JwtAuthFilter;
import com.cap.fatrip.auth.OAuth2SuccessHandler;
import com.cap.fatrip.repository.UserRepository;
import com.cap.fatrip.service.CustomOAuth2UserService;
import com.cap.fatrip.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig {
    /* OAuth */
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                // 계획 저장 등 db에 쓰는 모든 req. 아래는 예시
                .antMatchers("/", "/plan/write/**", "/gather/write/**", "/comment/write"
                , "/**/changeNickname").authenticated()
                .anyRequest().permitAll()
                .and()

                /* OAuth */
                .addFilterBefore(new JwtAuthFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class)
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint() // OAuth2 로그인 성공 후 가져올 설정들
                .userService(customOAuth2UserService); // 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
        return http.build();
    }
}