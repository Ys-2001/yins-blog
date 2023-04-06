package com.ysovo.yinsblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysovo.yinsblog.dao.ArticleDao;
import com.ysovo.yinsblog.dao.CategoryDao;
import com.ysovo.yinsblog.dto.CategoryBackDTO;
import com.ysovo.yinsblog.dto.CategoryDTO;
import com.ysovo.yinsblog.dto.CategoryOptionDTO;
import com.ysovo.yinsblog.entity.Article;
import com.ysovo.yinsblog.entity.Category;
import com.ysovo.yinsblog.exception.BizException;
import com.ysovo.yinsblog.service.CategoryService;
import com.ysovo.yinsblog.utils.BeanCopyUtils;
import com.ysovo.yinsblog.utils.PageUtils;
import com.ysovo.yinsblog.vo.CategoryVO;
import com.ysovo.yinsblog.vo.ConditionVO;
import com.ysovo.yinsblog.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * (Category)表服务实现类
 *
 * @author ys
 * @since 2023-04-06 14:02:00
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public PageResult<CategoryDTO> listCategories() {
        return new PageResult<>(categoryDao.listCategoryDTO(), Math.toIntExact(categoryDao.selectCount(null)));
    }

    @Override
    public PageResult<CategoryBackDTO> listBackCategories(ConditionVO condition) {
        // 查询分类数量
        Integer count = Math.toIntExact(categoryDao.selectCount(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords())));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询分类列表
        List<CategoryBackDTO> categoryList = categoryDao.listCategoryBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(categoryList, count);
    }

    @Override
    public List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition) {
        // 搜索分类
        List<Category> categoryList = categoryDao.selectList(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords())
                .orderByDesc(Category::getId));
        return BeanCopyUtils.copyList(categoryList, CategoryOptionDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(List<Integer> categoryIdList) {
        // 查询分类id下是否有文章
        Integer count = Math.toIntExact(articleDao.selectCount(new LambdaQueryWrapper<Article>()
                .in(Article::getCategoryId, categoryIdList)));
        if (count > 0) {
            throw new BizException("删除失败，该分类下存在文章");
        }
        categoryDao.deleteBatchIds(categoryIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateCategory(CategoryVO categoryVO) {
        // 判断分类名重复
        Category existCategory = categoryDao.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, categoryVO.getCategoryName()));
        if (Objects.nonNull(existCategory) && !existCategory.getId().equals(categoryVO.getId())) {
            throw new BizException("分类名已存在");
        }
        Category category = Category.builder()
                .id(categoryVO.getId())
                .categoryName(categoryVO.getCategoryName())
                .build();
        this.saveOrUpdate(category);
    }

}
