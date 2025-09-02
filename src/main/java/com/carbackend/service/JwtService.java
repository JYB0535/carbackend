package com.carbackend.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService { //메이븐 리포지토리에서 라이브러리 받아와서 했음

    //상수                            //마지막에 공백 하나 들어가있음 왜 ? 그냥 약속이라서
    //서버와 클라이언트가 주고받는 토큰 ==> HTTP Header 내 Authorization 헤더 값에 저장
    //예) Authorization Bearer dmfklejflekjslkkejlfAAADFWF <= 이런식으로 토큰 주고 응답 받을때도 Bearer 라고 있고 한칸 띄우고 그 뒤에 토큰 붙음
    //Authorization Bearer <토큰값>
    static final String PREFIX = "Bearer ";
    static final long EXPIRATIONTIME = 24*60*60*1000; //밀리초 단위라서 1000곱해주고 (60초*60: 1시간) * 24하루 = 86,400,000 ==> 하루
    static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); //무작위 비밀키 생성 //실제 운영할때는 이렇게 하면 안 된다. 비밀키는 고정된 값으로 서버가 가지고 있어야 한다

    //username(ID)를 받아서 JWT 실행
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //지금 만드는 토큰에서 어디에 뭘 넣는지 중요하다? 나중에 다시 받아서 파싱할때도 같은 곳에서 꺼내야한다. 서브젝트에 아이디 넣었으면 서브젝트에서 아이디 빼야함
                                        //현재 시간                     //만료 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SIGNING_KEY) //토큰에 서명 시그니처
                .compact(); //빌드 하고 나서 문자열 변환까지
    }

    //JWT를 받아서 username(ID)를 반환
                                //헤더 바디 등이 다 들어있는 객체?
    public String parseToken(HttpServletRequest request) {
        //요청 헤더에서 AUTHORIZATION 헤더 값을 가져온다.
        //예) token = Bearer <토큰값>
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && header.startsWith(PREFIX)) { //토큰이 널이 아니면서 Bearer로 시작 되니?
            JwtParser parser = Jwts.parserBuilder() //파싱
                    .setSigningKey(SIGNING_KEY)
                    .build(); //이렇게 하면 jwt 파서 라는걸 반환해준다.  //비어러는 걍 프리픽스라서 토큰만 넣어주면 된다
            String username = parser.parseClaimsJws(header.replace(PREFIX, ""))
                    .getBody()
                    .getSubject(); //서브젝트 안에 유저네임이 있었기 때문에 여기서 꺼내줘야한다
            if(username != null) {
                return username;
            }
        }
        return null; //위에 if 문까지 정상적으로 못 가거나 if 문에서 실패한 경우 (다시 로그인 페이지로 돌아간다)
    }
}
