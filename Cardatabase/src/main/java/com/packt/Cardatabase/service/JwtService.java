package com.packt.Cardatabase.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    static final long EXPIRATIONTIME=86400000;//하루를 밀리초로 계산한값 생존주기하루
    static final String PREFIX="Bearer";
    //비밀키 생성 시연용도로만 사용해야함
    //애플리케이션 구성에서 읽을수있음
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //토큰 생성
    public String getToken(String username){
        String token= Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME))
                .signWith(key)
                .compact();

        return token;
    }

    public String getAuthUser(HttpServletRequest request){
        String token=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token!=null){
            String user= Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX,""))
                    .getBody()
                    .getSubject();

            if(user!=null){
                return user;
            }
        }
        return null;

    }


}
