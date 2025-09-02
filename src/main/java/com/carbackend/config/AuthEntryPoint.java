package com.carbackend.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

//권한 없어서 401? 403? 나올때? 이게 없을때는 뭐 다 403으로 온다? 무슨 소리지 아까 이거 cars 헤더에 토큰 없이 했을때 403 나왔는데 이거 쓰고 체인에 추가해주니까 401이 나옴
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //SC_UNAUTHORIZED는 401임
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //import 스프링 어쩌고
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("인증에 실패했습니다. :" + authException.getMessage());

    }
}
