package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.mapper.SysUserMapper;
import org.lc.my_blog_api.service.LoginService;
import org.lc.my_blog_api.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.entity.UserVO;
import org.lc.my_blog_api.vo.result.ErrorCode;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 自动类型注入SysUserMapper
     */
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private LoginService loginService;


    /**
     * 根据文章id查询所属的作者
     *
     * @param articleId 文章id
     * @return 所属的用户实例
     */
    @Override
    public SysUser selectAuthor(Long articleId) {
        // 调用mybatis-plus中的接口
        SysUser sysUser = userMapper.selectById(articleId);
        if (sysUser == null) {
            // 如果没有作者默认值为lc_co
            sysUser = new SysUser();
            sysUser.setNickname("lc_co");
        }
        return sysUser;
    }

    /**
     * 查询指定用户名与密码的用户信息
     * @param account 用户名
     * @param password 密码
     * @return user实例
     */
    @Override
    public SysUser selectUser(String account, String password) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname)
                .eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password)
                .last("limit 1");
        return this.userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public Result currentUser(String token) {
        SysUser user  = this.loginService.currentUser(token);
        if(user == null){
            // token异常
            return ResultUtils.fail(false, ErrorCode.TOKEN_ERROR.getCode(),"token信息异常!",null);
        }
        // 返回uservo数据model
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return ResultUtils.success(userVO);
    }

    /**
     *
     * @param authorId 评论人id
     * @return
     */
    @Override
    public UserVO selectCommentAuthor(Long authorId) {
        // 调用mybatis-plus中的接口
        SysUser sysUser = userMapper.selectById(authorId);
        return castUserVO(sysUser);
    }

    /**
     * 将user Mapper下的实体类转换为VOModel数据
     * @param sysUser
     * @return
     */
    private UserVO castUserVO(SysUser sysUser) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(sysUser,userVO);
        return userVO;
    }
}
