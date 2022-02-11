package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.joda.time.DateTime;
import org.lc.my_blog_api.entity.Article;
import org.lc.my_blog_api.entity.ArticleBody;
import org.lc.my_blog_api.entity.ArticleTag;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.entity.dos.ArchiveArticles;
import org.lc.my_blog_api.mapper.ArticleMapper;
import org.lc.my_blog_api.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.utils.UserLocalThread;
import org.lc.my_blog_api.vo.entity.*;
import org.lc.my_blog_api.vo.params.ArticleParams;
import org.lc.my_blog_api.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ThreadService threadService;

    /**
     * 分页查询文章信息
     *
     * @param pageParams 分页信息
     * @return 文章列表
     */
    @Override
    public List<ArticleVO> listArticles(PageParams pageParams) {
        // 创建mybatis-plus分页对象
        Page<Article> articlePage = new Page<>();
        // 配置当前页以及页面容量
        articlePage.setCurrent(pageParams.getPage());
        articlePage.setSize(pageParams.getPageSize());
        // 添加查询条件包装器
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 按照创建时间,是否置顶降序排序
        lambdaQueryWrapper.orderByDesc(Article::getCreateDate, Article::getWeight);
        if (pageParams.getCategoryId() != null) {
            lambdaQueryWrapper.eq(Article::getCategoryId, pageParams.getCategoryId());
        }
        List<Long> articleIds = new ArrayList<>();
        if (pageParams.getTagId() != null) {
            // 从关联表中获取指定tagId的articleIdlist
            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            articleTagLambdaQueryWrapper.eq(ArticleTag::getTagId, pageParams.getTagId());
            List<ArticleTag> articleTags = this.articleTagService.list(articleTagLambdaQueryWrapper);
            // 将查询到的文章id添加到articleIds中
            articleTags.forEach(articleTag -> {
                articleIds.add(articleTag.getArticleId());
            });
            // 添加到article查询条件中
            lambdaQueryWrapper.in(Article::getId, articleIds);
        }
        Page<Article> articleList = this.articleMapper.selectPage(articlePage, lambdaQueryWrapper);
        List<Article> articles = articleList.getRecords();
        return castArticleVO(articles, true, true);
    }

    @Override
    public List<HostArticleVO> listHostArticles(int limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 等同于SELECT id,title FROM ms_article ORDER BY create_date  DESC limit 4
        lambdaQueryWrapper.select(Article::getId, Article::getTitle)
                .orderByDesc(Article::getCreateDate)
                .last("limit " + limit);
        List<Article> hostArticles = this.articleMapper.selectList(lambdaQueryWrapper);
        // 转换为vo数据
        return castHostArticleVO(hostArticles);
    }

    @Override
    public List<HostArticleVO> listNowArticles(int limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 等同于SELECT id,title FROM ms_article ORDER BY view_counts DESC limit 6
        lambdaQueryWrapper.select(Article::getId, Article::getTitle)
                .orderByDesc(Article::getViewCounts)
                .last("limit " + limit);
        List<Article> hostArticles = this.articleMapper.selectList(lambdaQueryWrapper);
        // 转换为vo数据
        return castHostArticleVO(hostArticles);
    }

    /**
     * 获取归档信息
     *
     * @return 归档信息list
     */
    @Override
    public List<ArchiveArticles> listArchivesArticles() {
        return this.articleMapper.listArchivesArticles();
    }

    /**
     * 转换为vo数据
     *
     * @param hostArticles 热门文章article对象
     * @return hostArticleVo 热门文章vo对象
     */
    private List<HostArticleVO> castHostArticleVO(List<Article> hostArticles) {
        ArrayList<HostArticleVO> articleVOList = new ArrayList<>();
        hostArticles.forEach(article -> {
            articleVOList.add(castHostArticleVO(article));
        });
        return articleVOList;
    }

    private HostArticleVO castHostArticleVO(Article article) {
        HostArticleVO hostArticleVO = new HostArticleVO();
        BeanUtils.copyProperties(article, hostArticleVO);
        return hostArticleVO;
    }

    /**
     * 将article数据库实体类列表转为article vo实体类列表
     *
     * @param articles 数据库实体类实例列表
     * @return articleVOList vo实体类实例列表
     */
    private List<ArticleVO> castArticleVO(List<Article> articles, boolean haveTag, boolean haveAuthor) {
        ArrayList<ArticleVO> articleVOList = new ArrayList<>();
        // 逐个遍历转换为ArticleVO实例对象
        articles.forEach(article -> {
            ArticleVO articleVO = castArticleVO(article, haveTag, haveAuthor, false, false);
            articleVOList.add(articleVO);
        });
        return articleVOList;
    }

    /**
     * 将article数据库实体类转为article vo实体类
     *
     * @param article 数据库实体类实例
     * @return article vo实体类实例
     */
    private ArticleVO castArticleVO(Article article, boolean haveTag, boolean haveAuthor, boolean haveBody, boolean haveCategory) {
        ArticleVO articleVO = new ArticleVO();
        // 复制相同属性
        BeanUtils.copyProperties(article, articleVO);
        // long类型日期转指定格式的字符串
        articleVO.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        Long articleId = article.getId();
        // 标签信息 并不是所有的文章都有标签信息
        if (haveTag) {
            // 根据文章id查询所属的标签list
            articleVO.setTags(this.tagService.listTags(articleId));
        }
        // 作者信息，并不是所有的文章都有作者信息
        if (haveAuthor) {
            // 根据文章id查询所属的作者
            SysUser user = this.sysUserService.selectAuthor(articleId);
            articleVO.setAuthor(user.getNickname());
        }
        // 文章主体信息
        if (haveBody) {
            // 根据文章id查询文章主体
            ArticleBodyVO articleBodyVO = this.articleBodyService.selectBody(article.getBodyId());
            articleVO.setBody(articleBodyVO);
        }
        // 文章类别信息
        if (haveBody) {
            // 根据文章id查询文章类别
            CategoryVO categoryVO = this.categoryService.selectCategory(article.getCategoryId());
            articleVO.setCategory(categoryVO);
        }
        return articleVO;
    }

    /**
     * @param articleId 文章id
     * @return
     */
    @Override
    public ArticleVO selectArticle(Long articleId) {
        Article article = this.articleMapper.selectById(articleId);
        threadService.updateArticleViewNum(articleId);
        return castArticleVO(article, true, true, true, true);
    }

    /**
     * 发布文章
     *
     * @param articleParams 文章参数
     * @return
     */
    @Override
    public ArticleVO publist(ArticleParams articleParams) {
        /**
         * 1.转换为article实体类对象
         * 2.articlebody关联表的添加
         * 3.标签关联表
         * 4.用户信息
         */
        // 查询前一个文章信息id
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Article::getId)
                .orderByDesc(Article::getId)
                .last("limit 1");
        Article preArticle = this.articleMapper.selectOne(lambdaQueryWrapper);
        Article article = new Article();
        article.setTitle(articleParams.getTitle());
        article.setSummary(articleParams.getSummary());
        article.setViewCounts(0);
        article.setCommentCounts(0);
        // 获取当前登录用户
        SysUser user = UserLocalThread.get();
        article.setAuthorId(user == null ? 0L : user.getId());
        article.setWeight(0);
        // 更新articleBody关联表
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(preArticle.getId() + 1);
        articleBody.setContent(articleParams.getBody().getContent());
        articleBody.setContentHtml(articleParams.getBody().getContentHtml());
        // 调用service接口保存关联表信息
        this.articleBodyService.save(articleBody);
        // 设置bodyId
        article.setBodyId(articleBody.getId());
        // 更新标签关联表
        List<TagVO> tags = articleParams.getTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            tags.forEach(tagVO -> {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(preArticle.getId() + 1);
                articleTag.setTagId(tagVO.getId());
                this.articleTagService.save(articleTag);
            });
        }
        article.setCreateDate(System.currentTimeMillis());
        CategoryVO category = articleParams.getCategory();
        // 设置类别信息
        article.setCategoryId(category.getId());
        int record = this.articleMapper.insert(article);
        // 返回文章信息
        Article newArticle = this.articleMapper.selectById(article.getId());
        if (record > 0) {
            return castArticleVO(newArticle, false, false, false, false);
        }
        return null;
    }


    @Override
    public List<ArticleVO> listArchiveArticles(PageParams pageParams) {
        // 创建mybatis-plus分页对象
        Page<Article> articlePage = new Page<>();
        // 配置当前页以及页面容量
        articlePage.setCurrent(pageParams.getPage());
        articlePage.setSize(pageParams.getPageSize());
        // 调用mapper接口
        System.out.println(pageParams.toString());
        IPage<Article> articleIPage = this.articleMapper.listArchiveArticles(articlePage, pageParams.getCategoryId(), pageParams.getTagId(), pageParams.getYear(), pageParams.getMonth());
        // 转换类型
        return castArticleVO(articleIPage.getRecords(),true,true);
    }

}
