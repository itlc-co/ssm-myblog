package org.lc.my_blog_api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lc.my_blog_api.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 查询指定limit的热门标签的标签idList
     * @param limit 指定limit
     * @return ids的list
     */
    List<Long> listHostTagIds(Integer limit);
}
