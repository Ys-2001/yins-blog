package com.ysovo.yinsblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysovo.yinsblog.entity.UserAuth;
import com.ysovo.yinsblog.vo.UserVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * (UserAuth)表服务接口
 *
 * @author ys
 * @since 2023-03-26 13:06:43
 */
public interface UserAuthService extends IService<UserAuth> {

    @Transactional(rollbackFor = Exception.class)
    void register(UserVO user);
}

