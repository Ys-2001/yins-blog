package com.ysovo.yinsblog.utils;

import com.ysovo.yinsblog.dto.UserDetailDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * 用户工具类
 *
 * @author ys
 * @date 2023/03/10
 */
@Component
public class UserUtils {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailDTO getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetailDTO) authentication.getPrincipal();
        } else {
            return null;
        }
    }


}
