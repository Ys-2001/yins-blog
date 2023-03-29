package com.ysovo.yinsblog.handler;

import com.alibaba.fastjson.JSON;
import com.ysovo.yinsblog.dao.UserAuthDao;
import com.ysovo.yinsblog.dto.UserDetailDTO;
import com.ysovo.yinsblog.dto.UserInfoDTO;
import com.ysovo.yinsblog.entity.UserAuth;
import com.ysovo.yinsblog.utils.BeanCopyUtils;
import com.ysovo.yinsblog.utils.TokenManager;
import com.ysovo.yinsblog.utils.UserUtils;
import com.ysovo.yinsblog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 登录成功处理
 *
 * @author ys
 * @date 2023/03/26
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    TokenManager tokenManager;

    /**
     * 更新用户信息
     */
    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtils.getLoginUser().getId())
                .ipAddress(UserUtils.getLoginUser().getIpAddress())
                .ipSource(UserUtils.getLoginUser().getIpSource())
                .lastLoginTime(UserUtils.getLoginUser().getLastLoginTime())
                .build();
        userAuthDao.updateById(userAuth);
    }

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserInfoDTO.class);
        userLoginDTO.setToken(tokenManager.createToken((UserDetailDTO) authentication.getPrincipal()));

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.ok(userLoginDTO)));
        // 更新用户ip，最近登录时间
        updateUserInfo();
    }
}
