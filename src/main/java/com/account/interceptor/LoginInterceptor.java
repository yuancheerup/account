package com.account.interceptor;

import com.account.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        try {
            // 解析token
            String[] data = TokenUtils.decodeToken();

            if (data.length == 0) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                log.info("请求 {} 被拦截！！！", request.getRequestURI());
                return false;
            }
            log.info("请求 {} 放行！！！", request.getRequestURI());
            return true;
        } catch (Exception e) {
            log.info("请求 {} 被拦截！！！", request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
