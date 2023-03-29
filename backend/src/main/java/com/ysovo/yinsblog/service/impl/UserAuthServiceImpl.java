package com.ysovo.yinsblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysovo.yinsblog.constant.CommonConst;
import com.ysovo.yinsblog.dao.UserAuthDao;
import com.ysovo.yinsblog.dao.UserInfoDao;
import com.ysovo.yinsblog.dao.UserRoleDao;
import com.ysovo.yinsblog.entity.UserAuth;
import com.ysovo.yinsblog.entity.UserInfo;
import com.ysovo.yinsblog.entity.UserRole;
import com.ysovo.yinsblog.enums.LoginTypeEnum;
import com.ysovo.yinsblog.enums.RoleEnum;
import com.ysovo.yinsblog.exception.BizException;
import com.ysovo.yinsblog.service.UserAuthService;
import com.ysovo.yinsblog.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * (UserAuth)表服务实现类
 *
 * @author ys
 * @since 2023-03-26 13:47:13
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthDao, UserAuth> implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserVO user) {
        // 校验账号是否合法
        if (checkUser(user)) {
            throw new BizException("邮箱已被注册！");
        }
        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(CommonConst.DEFAULT_NICKNAME + IdWorker.getId())
                .avatar("https://www.static.ysongblog.cn/avatar/f0861c446be4eb632ea07c064fb4af57.jpg")
                .build();
        userInfoDao.insert(userInfo);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleDao.insert(userRole);
        // 新增用户账号
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .loginType(LoginTypeEnum.EMAIL.getType())
                .build();
        userAuthDao.insert(userAuth);
    }



    private Boolean checkUser(UserVO user) {
        //查询用户名是否存在
        UserAuth userAuth = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }

}

