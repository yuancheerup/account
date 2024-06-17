package com.account.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TokenUtils {
    private static final String SIGN_KEY = "jxufe";
    private static final Long EXPIRE = 7200000L;

    // 生成Jwt令牌
    public static String createToken(String data) {
        return JWT.create()
                .withAudience(data)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(SIGN_KEY));
    }

    // 解析Jwt令牌
    public static String[] decodeToken() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("Authorization");

                // 打印接收到的Token
                log.info("接收到的Token: {}", token);

                if (token == null || token.isEmpty()) {
                    // 如果令牌为空，抛出异常
                    throw new RuntimeException("Token is missing");
                }

                // 创建用于验证令牌的算法对象
                Algorithm algorithm = Algorithm.HMAC256(SIGN_KEY);
                // 创建JWTVerifier对象用于验证令牌
                JWTVerifier verifier = JWT.require(algorithm).build();
                // 验证令牌并解析
                DecodedJWT decodedJWT = verifier.verify(token);

                // 获取令牌中的受众信息并返回
                String[] data = decodedJWT.getAudience().get(0).split("-");
                return data;

            }
            throw new RuntimeException("Token is missing");

        } catch (JWTDecodeException exception) {
            // Invalid token
            throw new RuntimeException("Invalid token", exception);
        }
    }
}
