package com.account;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest
class AccountApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testJwt() {
        String data = "admin" + "-" +  "123456" + "-" + "ADMIN";

        String token = JWT.create()
                .withAudience(data)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(Algorithm.HMAC256("yuan"));


        DecodedJWT decode = JWT.decode(token);
        System.out.println(decode.getAudience().get(0));

    }
}
