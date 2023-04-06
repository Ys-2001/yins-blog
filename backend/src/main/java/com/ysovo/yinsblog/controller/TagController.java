package com.ysovo.yinsblog.controller;



import com.ysovo.yinsblog.dto.TagBackDTO;
import com.ysovo.yinsblog.dto.TagDTO;
import com.ysovo.yinsblog.service.TagService;
import com.ysovo.yinsblog.vo.ConditionVO;
import com.ysovo.yinsblog.vo.PageResult;
import com.ysovo.yinsblog.vo.Result;
import com.ysovo.yinsblog.vo.TagVO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 标签控制器
 *
 * @author ys
 * @date 2023/04/06
 */
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     *
     * @return {@link Result<TagDTO>} 标签列表
     */
    @GetMapping("/tags")
    public Result<PageResult<TagDTO>> listTags() {
        return Result.ok(tagService.listTags());
    }

    /**
     * 查询后台标签列表
     *
     * @param condition 条件
     * @return {@link Result<TagBackDTO>} 标签列表
     */
    @GetMapping("/admin/tags")
    public Result<PageResult<TagBackDTO>> listTagBackDTO(ConditionVO condition) {
        return Result.ok(tagService.listTagBackDTO(condition));
    }

    /**
     * 搜索文章标签
     *
     * @param condition 条件
     * @return {@link Result<String>} 标签列表
     */
    @GetMapping("/admin/tags/search")
    public Result<List<TagDTO>> listTagsBySearch(ConditionVO condition) {
        return Result.ok(tagService.listTagsBySearch(condition));
    }

    /**
     * 添加或修改标签
     *
     * @param tagVO 标签信息
     * @return {@link Result<>}
     */
    @PostMapping("/admin/tags")
    public Result<?> saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return Result.ok();
    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id列表
     * @return {@link Result<>}
     */
    @DeleteMapping("/admin/tags")
    public Result<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return Result.ok();
    }

}

