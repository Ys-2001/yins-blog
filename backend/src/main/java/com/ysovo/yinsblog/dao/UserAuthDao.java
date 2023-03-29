package com.ysovo.yinsblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysovo.yinsblog.entity.UserAuth;
import org.springframework.stereotype.Repository;

/**
 * (UserAuth)表数据库访问层
 *
 * @author ys
 * @since 2023-03-26 13:27:26
 */
@Repository
public interface UserAuthDao extends BaseMapper<UserAuth> {

}

