package com.ysovo.yinsblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysovo.yinsblog.entity.ArticleTag;
import org.springframework.stereotype.Repository;

/**
 * (ArticleTag)表数据库访问层
 *
 * @author ys
 * @since 2023-04-06 14:02:00
 */
@Repository
public interface ArticleTagDao extends BaseMapper<ArticleTag> {

}

