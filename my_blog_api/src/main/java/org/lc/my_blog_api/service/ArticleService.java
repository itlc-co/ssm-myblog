package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.entity.dos.ArchiveArticles;
import org.lc.my_blog_api.vo.entity.ArticleVO;
import org.lc.my_blog_api.vo.entity.HostArticleVO;
import org.lc.my_blog_api.vo.params.ArticleParams;
import org.lc.my_blog_api.vo.params.PageParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询文章列表
     * @param pageParams  分页信息
     * @return  文章列表
     */
    List<ArticleVO> listArticles(PageParams pageParams);

    /**
     * 获取热门文章
     * @param limit
     * @return
     */
    List<HostArticleVO> listHostArticles(int limit);

    /**
     * 获取最新文章
     * @param limit
     * @return
     */
    List<HostArticleVO> listNowArticles(int limit);

    /**
     * 获取归档信息
     * @return 归档信息list
     */
    List<ArchiveArticles> listArchivesArticles();

    /**
     * 查询指定id的文章
     * @param articleId 文章id
     * @return 文章VO对象信息
     */
    ArticleVO selectArticle(Long articleId);

    /**
     * 发布文章
     * @param articleParams 文章参数
     * @return 操作数
     */
    ArticleVO publist(ArticleParams articleParams);

    /**
     * 按年月归档查询文章
     * @param pageParams 分页参数信息
     * @return
     */
    List<ArticleVO> listArchiveArticles(PageParams pageParams);


//    /**
//     * 修改指定id的文章的阅读数+1
//     * @param articleId
//     */
//    void updateArticleViewNum(Long articleId);
}
