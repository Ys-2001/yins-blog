package com.ysovo.yinsblog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ys
 * @date 2023/04/06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 文章分类
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<String> tagNameList;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 原文链接
     */
    private String originalUrl;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    private Integer status;

}
