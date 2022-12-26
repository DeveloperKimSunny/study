package indi.gradle.spring.study.commons.utils.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String secretKey;
    private long expireMilliSeconds;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey
                            , @Value("${security.jwt.token.expire-length}") long expireMilliSeconds){
        this.secretKey = secretKey;
        this.expireMilliSeconds = expireMilliSeconds;
    }

    // 토큰생성
    public String createToken(String subject){
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("userId", "devtest123");
        claims.put("userName", "개발테스트");

        Date expiredTime = new Date(new Date().getTime() + expireMilliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰 값 추출
    public String getSubject(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // DTO 형태 토큰값 생성
    public String createToken(Object userInfo){
        Claims claims = Jwts.claims();
        claims.put("userInfo", userInfo);

        Date expiredTime = new Date(new Date().getTime() + expireMilliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Object getTknValue(String token, String claimsKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(claimsKey);
    }

    // 유효 토큰 확인
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())) return false;
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }

    }

}
