package org.lc.my_blog_api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lc.my_blog_api.entity.Tag;
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
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据指定文章的id查询所属的标签信息(可能不止一个标签)
     * @param articleId  指定文章的id
     * @return 标签信息list
     */
    List<Tag> listTags(Long articleId);

}
