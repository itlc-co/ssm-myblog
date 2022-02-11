package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;
import org.lc.my_blog_api.entity.ArticleTag;
import org.lc.my_blog_api.entity.Tag;
import org.lc.my_blog_api.mapper.ArticleTagMapper;
import org.lc.my_blog_api.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.vo.entity.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 查询指定limit的热门标签的标签idList
     * @param limit 指定limit
     * @return 标签id的List
     */
    @Override
    public List<Long> listHostTagIds(Integer limit) {
        return this.articleTagMapper.listHostTagIds(limit);
    }
}
