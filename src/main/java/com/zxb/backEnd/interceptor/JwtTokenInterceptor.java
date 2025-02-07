package com.zxb.backEnd.interceptor;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.utils.JwtUtils;
import com.zxb.backEnd.utils.UserContext;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) return true;

        // 1. 从请求投获取token
        String token = request.getHeader("Authorization");

        // 2. 校验令牌
        try {
            log.info("jwt校验: {}", token);
            Claims claims = JwtUtils.parsePayload(token);
            Integer userId = (Integer) claims.get("id");
            String userName = claims.getSubject();
            log.info("当前用户id：{}", userId);
            User user = new User();
            user.setUsername(userName);
            user.setId(userId);
            UserContext.setUser(user);
            // 3. 放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
