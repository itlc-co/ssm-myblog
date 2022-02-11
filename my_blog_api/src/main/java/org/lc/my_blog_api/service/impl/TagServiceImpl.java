package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.lc.my_blog_api.entity.Tag;
import org.lc.my_blog_api.mapper.TagMapper;
import org.lc.my_blog_api.service.ArticleTagService;
import org.lc.my_blog_api.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.vo.entity.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 自动注入tagMapper
     */
    @Autowired
    private TagMapper tagMapper;


    @Autowired
    private ArticleTagService articleTagService;
    /**
     * 根据指定文章的id查询所属的标签信息(可能不止一个标签)
     * @param articleId 文章的id
     * @return 文章的标签信息list
     */
    @Override
    public List<TagVO> listTags(Long articleId) {
        List<Tag> tags = this.tagMapper.listTags(articleId);
        return castVO(tags);
    }

    @Override
    public List<TagVO> listHostTags(Integer limit) {
        // 查询指定limit的热门标签的标签idList
        List<Long> hostTagIds = this.articleTagService.listHostTagIds(limit);
        if (CollectionUtils.isEmpty(hostTagIds)){
            return Collections.emptyList();
        }
        // 查询指定idList查询指定的标签信息
        List<Tag> hostTags = this.tagMapper.selectBatchIds(hostTagIds);
        // 实体类转换
        return castVO(hostTags);
    }

    /**
     * 获取所有标签信息
     * @return 所有标签信息
     */
    @Override
    public List<TagVO> listTags() {
        List<Tag> tags = this.tagMapper.selectList(new QueryWrapper<>());
        return castVO(tags);
    }

    /**
     * 转换数据库实体类与用户层实体类vo数据
     * @param tags 数据库实体类标签list
     * @return tagvo 用户层标签信息list
     */
    private List<TagVO> castVO(List<Tag> tags) {
        ArrayList<TagVO> tagVOS = new ArrayList<>();
        tags.forEach(tag -> tagVOS.add(castVO(tag)));
        return tagVOS;
    }

    private TagVO castVO(Tag tag) {
        TagVO tagVO = new TagVO();
        BeanUtils.copyProperties(tag,tagVO);
        return tagVO;
    }


    /**
     *
     * @param tagId 标签id
     * @return
     */
    @Override
    public TagVO selectTagById(Long tagId) {
        Tag tag = this.tagMapper.selectById(tagId);
        return castVO(tag);
    }

}
