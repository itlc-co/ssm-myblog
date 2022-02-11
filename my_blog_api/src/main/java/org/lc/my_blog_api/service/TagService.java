package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.Tag;
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
public interface TagService extends IService<Tag> {

    /**
     * 根据指定文章的id查询所属的标签信息(可能不止一个标签)
     * @param articleId 文章的id
     * @return 标签信息list
     */
    List<TagVO> listTags(Long articleId);

    /**
     * 指定limit的热门标签list
     * @param limit 最多数量
     * @return tagVoList
     */
    List<TagVO> listHostTags(Integer limit);

    /**
     *  返回所有标签信息
     * @return 所有标签信息
     */
    List<TagVO> listTags();

    /**
     * 返回指定标签id的信息
     * @param tagId 标签id
     * @return
     */
    TagVO selectTagById(Long tagId);
}


