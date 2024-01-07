package com.api.MvcApi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션
@Configuration // 이 파일이 스프링의 환경 설정 파일임을 의미하는 어노테이션
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder를 Bean 등록
    // spring Security에서 지원하는 비밀번호 단방향 암호화 인터페이스 객체
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // spring security 기능 비활성화
    // Spring Security의 웹 보안 구성을 변경하기 위한 인터페이스
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**"); // "/static/**' 패턴의 URL에 대한 보안 검사 무시
    }


    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(cs-> cs.disable())    // 보안 설정
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(h->h.disable());
        http  // 권한 설정
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user1/**").hasRole("USER")
            .requestMatchers("/**").permitAll()
            .anyRequest().permitAll() // 그외 나머지 요청은 모두 거절
            );
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
