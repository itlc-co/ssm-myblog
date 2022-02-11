package org.lc.my_blog_api.controller;


import org.apache.commons.lang3.ObjectUtils;
import org.lc.my_blog_api.service.TagService;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.entity.TagVO;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
@RequestMapping("/tags")
public class TagController {


    @Autowired
    private TagService tagService;

    /**
     *
     * @return 处理热门文章
     */
    @GetMapping("/hot")
    public Result hostTags(){
        int limit = 6;
        // 调用service接口获取热门标签
        List<TagVO> hostTags = this.tagService.listHostTags(limit);
        // 结果集封装
        Result result = null;
        if (CollectionUtils.isEmpty(hostTags)){
            result = ResultUtils.fail("");
        }else{
            result = ResultUtils.success(hostTags);
        }
        return result;
    }

    /**
     *  处理tags请求
     * @return
     */
    @GetMapping()
    public Result tags(){
        List<TagVO> tagVOS = this.tagService.listTags();
        // 结果集封装
        Result result = null;
        if (CollectionUtils.isEmpty(tagVOS)){
            result = ResultUtils.fail("");
        }else{
            result = ResultUtils.success(tagVOS);
        }
        return result;
    }

    @GetMapping("/detail")
    public Result tagDetails(){
        List<TagVO> tagVOS = this.tagService.listTags();
        // 结果集封装
        Result result = null;
        if (CollectionUtils.isEmpty(tagVOS)){
            result = ResultUtils.fail("");
        }else{
            result = ResultUtils.success(tagVOS);
        }
        return result;
    }

    @GetMapping("/detail/{tagId}")
    public Result tagDetails(@PathVariable("tagId") Long tagId){
        TagVO tagVO = this.tagService.selectTagById(tagId);
        // 结果集封装
        Result result = null;
        if (ObjectUtils.isEmpty(tagVO)){
            result = ResultUtils.fail("");
        }else{
            result = ResultUtils.success(tagVO);
        }
        return result;
    }

}
