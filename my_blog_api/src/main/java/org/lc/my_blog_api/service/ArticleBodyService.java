package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.ArticleBody;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.vo.entity.ArticleBodyVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface ArticleBodyService extends IService<ArticleBody> {

    /**
     * 根据主体id查询文章主体
     * @param bodyId 主体id
     * @return 文章主体
     */
    ArticleBodyVO selectBody(Long bodyId);
}
