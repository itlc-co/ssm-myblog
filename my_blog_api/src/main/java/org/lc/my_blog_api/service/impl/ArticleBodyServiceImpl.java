package org.lc.my_blog_api.service.impl;

import org.lc.my_blog_api.entity.ArticleBody;
import org.lc.my_blog_api.mapper.ArticleBodyMapper;
import org.lc.my_blog_api.service.ArticleBodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.vo.entity.ArticleBodyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Service
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody> implements ArticleBodyService {

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    /**
     *
     * @param bodyId 主体id
     * @return
     */
    @Override
    public ArticleBodyVO selectBody(Long bodyId) {
        ArticleBody articleBody = this.articleBodyMapper.selectById(bodyId);
        return castArticleBodyVO(articleBody);
    }

    /**
     * 将articleBody Mapper下的实体类转为articleBodyVOModel数据
     * @param articleBody
     * @return
     */
    private ArticleBodyVO castArticleBodyVO(ArticleBody articleBody) {
        ArticleBodyVO articleBodyVO = new ArticleBodyVO();
        BeanUtils.copyProperties(articleBody,articleBodyVO);
        return articleBodyVO;
    }
}
