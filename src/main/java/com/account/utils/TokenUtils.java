package com.account.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Component
public class TokenUtils {
    private static String signKey = "jxufe";
    private static Long expire = 7200000L;

    // 生成Jwt令牌
    public static String createToken(String data) {
        return JWT.create()
                .withAudience(data)
                .withExpiresAt(new Date(System.currentTimeMillis() + expire))
                .sign(Algorithm.HMAC256(signKey));
    }

    // 解析Jwt令牌
    public static String[] decodeToken() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("Authorization");
                DecodedJWT decode = JWT.decode(token);
                String[] data = decode.getAudience().get(0).split("-");

                return data;
            }
            return null;

        } catch (JWTDecodeException exception) {
            // Invalid token
            throw new RuntimeException("Invalid token", exception);
        }
    }
}
