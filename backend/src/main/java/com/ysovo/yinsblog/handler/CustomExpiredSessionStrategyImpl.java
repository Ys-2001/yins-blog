package com.ysovo.yinsblog.handler;

import com.alibaba.fastjson.JSON;
import com.ysovo.yinsblog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author ys
 * @date 2023/03/26
 */
@Component
public class CustomExpiredSessionStrategyImpl implements SessionInformationExpiredStrategy, Serializable
{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException
    {
        String msg = "您的账号已经在其它设备登录,如果非本人操作，请立即修改密码！";
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.fail(msg)));
    }
}