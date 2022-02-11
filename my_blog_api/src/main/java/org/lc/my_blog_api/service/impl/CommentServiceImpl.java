package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.joda.time.DateTime;
import org.lc.my_blog_api.entity.Comment;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.mapper.CommentMapper;
import org.lc.my_blog_api.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.service.SysUserService;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.utils.UserLocalThread;
import org.lc.my_blog_api.vo.entity.CommentVO;
import org.lc.my_blog_api.vo.entity.UserVO;
import org.lc.my_blog_api.vo.params.CommentParams;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.PipedReader;
import java.util.ArrayList;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService userService;


    @Override
    public Result selectCommentsArticle(Long articleId) {
        // 设置LambdaQueryWrapper条件
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper
                // 该文章的评论
                .eq(Comment::getArticleId,articleId)
                // 父评论level
                .eq(Comment::getLevel,1);
        List<Comment> comment =  this.commentMapper.selectList(commentLambdaQueryWrapper);
        List<CommentVO> commentVOS = castCommentVO(comment);
        // 封装结果集
        Result result = null;
        if(!CollectionUtils.isEmpty(commentVOS)){
            result = ResultUtils.success(commentVOS);
        }else{
            result = ResultUtils.success(true,200,"没有评论信息！",null);
        }
        return result;
    }

    /**
     * 添加评论信息
     * @param commentParams 评论参数
     * @return 结果集
     */
    @Override
    public Result addComment(CommentParams commentParams) {
        // 创建comment实体类实例对象通过参数
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParams,comment);
        Long parent = commentParams.getParent();
        // 如果当前父评论id为null则说明当前为父评论
        comment.setParentId(parent == null ? 0L: parent);
        Long toUserId = commentParams.getToUserId();
        // 如果当前评论对象为null则说明为父评论因此没有评论对象
        comment.setToUid(toUserId == null ? 0L :toUserId);
        if (ObjectUtils.isEmpty(parent) || parent == 0){
            // 父评论为”1“
            comment.setLevel("1");
        }else{
            // 非父评论为”2“
            comment.setLevel("2");
        }
        // 当前登录用户也就是当前评论人
        SysUser user = UserLocalThread.get();
        comment.setAuthorId(user.getId());
//        设置创建时间
        comment.setCreateDate(System.currentTimeMillis());
        int recordNum = this.commentMapper.insert(comment);
        // 结果集封装
        Result result = null;
        if(recordNum > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("系统异常!");
        }
        return result;
    }

    /**
     * 将Comment  Mapper下的实体类批量转换为VOModel数据
     * @param comments
     * @return
     */
    private List<CommentVO> castCommentVO(List<Comment> comments) {
        ArrayList<CommentVO> commentVOS = new ArrayList<>();
        comments.forEach(comment -> {
            commentVOS.add(castCommentVO(comment));
        });
        return commentVOS;
    }


    /**
     * 将Comment  Mapper下的实体类转换为VOModel数据
     * @param comment
     * @return
     */
    private CommentVO castCommentVO(Comment comment){
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment,commentVO);
        // 时间格式化
        commentVO.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        // 获取父评论人信息
        Long authorId = comment.getAuthorId();
        UserVO userVO = this.userService.selectCommentAuthor(authorId);
        commentVO.setAuthor(userVO);
        // 子评论信息
        // 当前评论的子评论信息，当前id为子评论的父id
        List<CommentVO> sonComments = this.selectCommentsParent(comment.getId());
        commentVO.setChildrens(sonComments);
        if (!"1".equals(comment.getLevel())) {
            // 子评论人信息
            Long commentToUid = comment.getToUid();
            UserVO toUserVO = this.userService.selectCommentAuthor(commentToUid);
            commentVO.setToUser(toUserVO);
        }
        return commentVO;
    }

    /**
     * 根据父评论id查询子评论信息
     * @param parentId 父评论id
     */
    private List<CommentVO> selectCommentsParent(Long parentId) {
        // 设置LambdaQueryWrapper条件
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper
                // 该文章的评论
                .eq(Comment::getParentId,parentId)
                // 子评论Level
                .eq(Comment::getLevel,2);
        List<Comment> comment =  this.commentMapper.selectList(commentLambdaQueryWrapper);
        return castCommentVO(comment);
    }


}
