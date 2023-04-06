package com.ysovo.yinsblog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签VO
 *
 * @author ys
 * @date 2023/04/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String tagName;

}
