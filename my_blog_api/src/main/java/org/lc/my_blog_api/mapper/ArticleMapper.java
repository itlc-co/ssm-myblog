package org.lc.my_blog_api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lc.my_blog_api.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.lc.my_blog_api.entity.dos.ArchiveArticles;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取归档信息
     * @return 归档信息list
     */
    List<ArchiveArticles> listArchivesArticles();

    /**
     * 自定义sql实现按年月归档的文章信息
     * @param articlePage  分页信息
     * @param categoryId 类别信息
     * @param tagId 标签信息
     * @param year 年
     * @param month 月
     * @return 按年月归档的文章信息
     */
    IPage<Article> listArchiveArticles(Page<Article> articlePage, @Param("categoryId") Long categoryId,@Param("tagId") Long tagId, @Param("year") String year,@Param("month") String month);
}
