package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.vo.entity.TagVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 查询指定limit的热门标签的标签idList
     * @param limit 指定limit
     * @return 标签id的List
     */
    List<Long> listHostTagIds(Integer limit);
}
