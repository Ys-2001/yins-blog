package com.ysovo.yinsblog.service;

import com.ysovo.yinsblog.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysovo.yinsblog.dto.CategoryBackDTO;
import com.ysovo.yinsblog.dto.CategoryDTO;
import com.ysovo.yinsblog.dto.CategoryOptionDTO;
import com.ysovo.yinsblog.entity.Category;
import com.ysovo.yinsblog.vo.CategoryVO;
import com.ysovo.yinsblog.vo.ConditionVO;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author ys
 * @since 2023-04-06 14:02:00
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    PageResult<CategoryDTO> listCategories();

    /**
     * 查询后台分类
     *
     * @param conditionVO 条件
     * @return {@link PageResult<CategoryBackDTO>} 后台分类
     */
    PageResult<CategoryBackDTO> listBackCategories(ConditionVO conditionVO);

    /**
     * 搜索文章分类
     *
     * @param condition 条件
     * @return {@link List <CategoryOptionDTO>} 分类列表
     */
    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition);

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     */
    void deleteCategory(List<Integer> categoryIdList);

    /**
     * 添加或修改分类
     *
     * @param categoryVO 分类
     */
    void saveOrUpdateCategory(CategoryVO categoryVO);

}

