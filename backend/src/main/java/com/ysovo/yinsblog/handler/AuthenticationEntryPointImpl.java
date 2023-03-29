package com.ysovo.yinsblog.handler;

import com.alibaba.fastjson.JSON;
import com.ysovo.yinsblog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户未登录处理
 *
 * @author ys
 * @date 2023/03/19
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    /**
     * Commences an authentication scheme.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.fail("用户未登录")));
    }
}
