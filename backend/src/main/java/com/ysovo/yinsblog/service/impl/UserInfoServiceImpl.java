package com.ysovo.yinsblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysovo.yinsblog.dao.UserInfoDao;
import com.ysovo.yinsblog.entity.UserInfo;
import com.ysovo.yinsblog.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * (UserInfo)表服务实现类
 *
 * @author ys
 * @since 2023-03-26 13:47:13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

}

