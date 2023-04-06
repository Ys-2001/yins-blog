package com.ysovo.yinsblog.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 逻辑删除
 *
 * @author ys
 * @date 2023/04/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteVO {

    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    private List<Integer> idList;

    /**
     * 状态值
     */
    @NotNull(message = "状态值不能为空")
    private Integer isDelete;

}
