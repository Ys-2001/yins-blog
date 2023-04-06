package com.ysovo.yinsblog.strategy;

import com.ysovo.yinsblog.dto.ArticleSearchDTO;

import java.util.List;

/**
 * 搜索策略
 *
 * @author ys
 * @date 2023/04/27
 */
public interface SearchStrategy {

    /**
     * 搜索文章
     *
     * @param keywords 关键字
     * @return {@link List<ArticleSearchDTO>} 文章列表
     */
    List<ArticleSearchDTO> searchArticle(String keywords);

}
