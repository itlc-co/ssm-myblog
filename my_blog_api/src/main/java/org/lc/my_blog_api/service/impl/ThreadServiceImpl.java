package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import org.lc.my_blog_api.entity.Article;
import org.lc.my_blog_api.service.ArticleService;
import org.lc.my_blog_api.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service.impl
 * @ClassName: ThreadServiceImpl
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 16:51
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private ArticleService articleService;

    /**
     *  线程池操作更新阅读数
     * @param articleId 文章id
     */
    @Async("tackExecutor")
    @Override
    public void updateArticleViewNum(Long articleId) {
        // 获取原来数据库中的文章阅读数
        Article article = this.articleService.getById(articleId);
        Integer viewCount = article.getViewCounts();
        // 设置修改后的文章阅读数原来的阅读数+1
        Integer newViewCount = viewCount + 1;
        // 创建LambdaUpdateWrapper
        LambdaUpdateWrapper<Article> lambdaUpdateWrapper = new LambdaUpdateWrapper<Article>();
        lambdaUpdateWrapper
                // 添加id条件
                .eq(Article::getId,articleId)
                // 添加阅读数未必修改过的条件  (乐观锁，判断version字段，从而判断出阅读数在修改之前是否已经被其他线程修改过)
                .eq(Article::getViewCounts,viewCount)
                // 更新阅读数
                .set(Article::getViewCounts,newViewCount);
        // 更新操作
        this.articleService.update(lambdaUpdateWrapper);
    }

}
