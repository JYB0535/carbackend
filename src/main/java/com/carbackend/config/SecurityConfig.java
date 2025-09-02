package com.carbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity //시큐리티 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthEntryPoint authEntryPoint;

    //로그인 할때는 가능해야한다 로그인할때부터 필터에 걸리머ㅕㄴ 할 수 업승ㅁ
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //1. csrf 끄고
        //2. 세션 정책 바꾸고
        //3. 로그인 요청 다 받고

        http            //AbstractHttpConfigurer::disable 람다식으로 바꾸면 이거임
                .csrf((CsrfConfigurer<HttpSecurity> csrf) -> csrf.disable()) //csrf도 토큰이라서 우리는 jwt토큰 쓰고 있기때문에 굳이 중복으로 쓸 필요 없다
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 정책 바꾸기 (사용안하는거로
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().authenticated()) //퍼밋 올 제외하고는 인증된 유저만 가능하다
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((ex) ->
                        ex.authenticationEntryPoint(authEntryPoint));

        return http.build();
    }


    //패스워드 인코더를 등록해주면
    @Bean
    public PasswordEncoder passwordEncoder() {
        //인코딩 없이 진행
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();  //exception 발생할 수도 있어서 떠넘긴다
        //AuthenticationManager 이 타입이 빈으로 관리된다.
    }


}
