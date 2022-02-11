package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.vo.params.CommentParams;
import org.lc.my_blog_api.vo.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface CommentService extends IService<Comment> {

    /**
     * 查询指定文章id的评论信息
     * @param articleId 文章id
     * @return 结果集
     */
    Result selectCommentsArticle(Long articleId);

    /**
     * 添加评论信息
     * @param commentParams 评论参数
     * @return 结果集
     */
    Result addComment(CommentParams commentParams);
}
