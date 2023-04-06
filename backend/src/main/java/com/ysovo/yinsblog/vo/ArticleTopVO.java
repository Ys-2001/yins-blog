package com.ysovo.yinsblog.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章置顶信息
 *
 * @author ys
 * @date 2023/04/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTopVO {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 置顶状态
     */
    @NotNull(message = "置顶状态不能为空")
    private Integer isTop;

}
