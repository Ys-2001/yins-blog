package com.ysovo.yinsblog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分类VO
 *
 * @author ys
 * @date 2023/04/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    private String categoryName;

}
