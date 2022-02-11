package org.lc.my_blog_api.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.lc.my_blog_api.common.aop.LogAnnotation;
import org.lc.my_blog_api.common.cache.CacheAnnotation;
import org.lc.my_blog_api.entity.dos.ArchiveArticles;
import org.lc.my_blog_api.service.ArticleService;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.entity.ArticleVO;
import org.lc.my_blog_api.vo.entity.HostArticleVO;
import org.lc.my_blog_api.vo.params.ArticleParams;
import org.lc.my_blog_api.vo.params.PageParams;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping()
    //    添加自定义的日志注解表示对此接口记录日志
    @LogAnnotation(module = "文章", operator = "文章列表操作")
    @CacheAnnotation(expire = 5*60*1000,key = "host_articles")
    public Result articles(@RequestBody PageParams pageParams) {
        // 调用service接口实现文章的分页查询
        List<ArticleVO> articleVOList = null;
        if (StringUtils.hasLength(pageParams.getYear()) && StringUtils.hasLength(pageParams.getMonth())) {
            // 携带年月参数信息为归档查询
            articleVOList = articleService.listArchiveArticles(pageParams);
        } else {
            // 未携带则为全查询
            articleVOList = articleService.listArticles(pageParams);
        }
        Result result = null;
        if (articleVOList!=null) {
            // 请求成功结果集
            result = ResultUtils.success(articleVOList);
        } else {
            // 请求失败结果集
            result = ResultUtils.fail("");
        }
        return result;
    }


    @PostMapping("/hot")
    @CacheAnnotation(expire = 5*60*1000,key = "host_articles")
    public Result hostArticles() {
        // 显示最多的4个的热门文章
        int limit = 4;
        // 调用service接口获取热门文章
        List<HostArticleVO> articles = this.articleService.listHostArticles(limit);
        // 结果集封装
        Result result = null;
        if (!CollectionUtils.isEmpty(articles)) {
            result = ResultUtils.success(articles);
        } else {
            result = ResultUtils.fail("");
        }
        return result;
    }

    @PostMapping("/new")
    public Result nowArticles() {
        // 显示最多的4个的热门文章
        int limit = 4;
        // 调用service接口获取热门文章
        List<HostArticleVO> articles = this.articleService.listNowArticles(limit);
        // 结果集封装
        Result result = null;
        if (!CollectionUtils.isEmpty(articles)) {
            result = ResultUtils.success(articles);
        } else {
            result = ResultUtils.fail("");
        }
        return result;
    }

    @PostMapping("/listArchives")
    public Result listArchivesArticles() {
        // 调用service接口获取归档信息
        List<ArchiveArticles> archiveArticles = this.articleService.listArchivesArticles();
        // 结果集封装
        Result result = null;
        if (!CollectionUtils.isEmpty(archiveArticles)) {
            result = ResultUtils.success(archiveArticles);
        } else {
            result = ResultUtils.fail("");
        }
        return result;
    }


    @PostMapping("/view/{articleId}")
    public Result viewArticle(@PathVariable("articleId") Long articleId) {
        ArticleVO articleVO = this.articleService.selectArticle(articleId);
        // 结果集封装
        Result result = null;
        if (articleVO != null) {
            result = ResultUtils.success(articleVO);
        } else {
            result = ResultUtils.fail(false, 500, "不存在该文章!", null);
        }
        return result;
    }


    /**
     * 发布文章
     *
     * @param articleParams 文章参数
     * @return
     */
    @PostMapping("/publish")
    public Result publistArticle(@RequestBody ArticleParams articleParams) {
        // 调用service接口添加文章
        ArticleVO articleVO = this.articleService.publist(articleParams);
        // 结果集封装
        Result result = null;
        if (!ObjectUtils.isEmpty(articleVO)) {
            result = ResultUtils.success(articleVO);
        } else {
            result = ResultUtils.fail(false, 500, "系统异常", null);
        }
        return result;
    }

}
