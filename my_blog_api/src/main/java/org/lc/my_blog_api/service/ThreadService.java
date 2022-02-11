package org.lc.my_blog_api.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service
 * @ClassName: ThreadService
 * @Description: 线程处理的service接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 16:52
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface ThreadService {

    /**
     * 根据文章id更新阅读数
     * @param articleId 文章id
     */
    void updateArticleViewNum(Long articleId);
}
