package com.ysovo.yinsblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysovo.yinsblog.dto.CategoryBackDTO;
import com.ysovo.yinsblog.dto.CategoryDTO;
import com.ysovo.yinsblog.entity.Category;
import com.ysovo.yinsblog.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author ys
 * @since 2023-04-06 14:02:00
 */
@Repository
public interface CategoryDao extends BaseMapper<Category> {

    /**
     * 查询分类和对应文章数量
     *
     * @return 分类列表
     */
    List<CategoryDTO> listCategoryDTO();

    /**
     * 查询后台分类列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<CategoryBackDTO>} 分类列表
     */
    List<CategoryBackDTO> listCategoryBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

}

