package org.lc.my_blog_api.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.lc.my_blog_api.service.CategoryService;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.entity.CategoryVO;
import org.lc.my_blog_api.vo.entity.TagVO;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @return 返回所有类型信息
     */
    @GetMapping()
    public Result categorys(){
        // 调用service接口
        List<CategoryVO> categoryVOList =  categoryService.listAllCategorys();
        // 结果集封装
        Result result = null;
        if (CollectionUtils.isEmpty(categoryVOList)){
            result = ResultUtils.fail("不存在类型信息!");
        }else{
            result = ResultUtils.success(categoryVOList);
        }
        return result;
    }


    @GetMapping("/detail")
    public Result categoryDetails(){
        // 调用service接口
        List<CategoryVO> categoryVOList =  categoryService.listAllCategorys();
        // 结果集封装
        Result result = null;
        if (CollectionUtils.isEmpty(categoryVOList)){
            result = ResultUtils.fail("不存在类型信息!");
        }else{
            result = ResultUtils.success(categoryVOList);
        }
        return result;
    }

    @GetMapping("/detail/{categoryId}")
    public Result categoryDetailsById(@PathVariable("categoryId") Long categoryId){
        // 调用service接口
        CategoryVO categoryVO =  categoryService.categoryDetailsById(categoryId);
        // 结果集封装
        Result result = null;
        if (ObjectUtils.isEmpty(categoryVO)){
            result = ResultUtils.fail("不存在类型信息!");
        }else{
            result = ResultUtils.success(categoryVO);
        }
        return result;
    }
}
