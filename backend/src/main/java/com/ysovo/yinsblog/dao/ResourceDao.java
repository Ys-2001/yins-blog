package com.ysovo.yinsblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysovo.yinsblog.entity.Resource;
import org.springframework.stereotype.Repository;

/**
 * (Resource)表数据库访问层
 *
 * @author ys
 * @since 2023-03-26 13:27:27
 */
@Repository
public interface ResourceDao extends BaseMapper<Resource> {

}

