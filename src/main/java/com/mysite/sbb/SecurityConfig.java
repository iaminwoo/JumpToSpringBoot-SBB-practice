package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizeHttpRequests ->
                // "/**"은 모든 경로를 의미한다.
                authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**"))
                        .permitAll()) // 모든 경로에 대해 허가함 (로그인 없이도)
                .csrf(csrf ->
                        // csrf는 토큰 관련. 외부 공격 방어 목적
                        // h2 콘솔 관련 허가 ( /h2-console/ 로 시작하는 모든 URL은 CSRF 검증 하지 않는다.)
                        csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                // 프레임 구조 페이지 클럭재킹 방어 관련
                .headers(headers ->
                        headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                                // 프레임에 포함된 웹 페이지가 동일한 사이트 (SAME ORIGIN)에서 제공되면 허용
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                        )))
        ;
        return http.build();
    }
}
