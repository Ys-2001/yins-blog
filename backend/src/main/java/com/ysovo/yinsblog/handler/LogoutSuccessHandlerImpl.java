package com.ysovo.yinsblog.handler;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.ysovo.yinsblog.dto.UserDetailDTO;
import com.ysovo.yinsblog.utils.TokenManager;
import com.ysovo.yinsblog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

/**
 * 注销处理
 *
 * @author ys
 * @date 2023/03/26
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenManager tokenManager;




    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailDTO loginUser = tokenManager.getLoginUser(request);
        if(ObjectUtil.isNotNull(loginUser))
        {
            //移除
            tokenManager.removeToken(loginUser.getToken());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.ok("退出成功")));
    }
}
