package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.vo.entity.UserVO;
import org.lc.my_blog_api.vo.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface SysUserService extends IService<SysUser> {

    /**
     *  根据文章id查询所属的作者
     * @param articleId   文章id
     * @return 所属的用户实例对象
     */
    SysUser selectAuthor(Long articleId);

    /**
     *  根据指定的用户名以及密码查询用户
     * @param account 用户名
     * @param password 密码
     * @return 用户实例
     */
    SysUser selectUser(String account, String password);

    /**
     * 根据token信息获取用户信息
     * @param token token信息
     * @return 结果集
     */
    Result currentUser(String token);

    /**
     * 根据指定的评论人id查询评论用户信息
     * @param authorId 评论人id
     * @return 用户信息
     */
    UserVO selectCommentAuthor(Long authorId);
}
