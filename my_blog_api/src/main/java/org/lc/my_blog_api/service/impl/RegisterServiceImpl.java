package org.lc.my_blog_api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.mapper.SysUserMapper;
import org.lc.my_blog_api.service.RegisterService;
import org.lc.my_blog_api.service.SysUserService;
import org.lc.my_blog_api.utils.JWTUtils;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.ErrorCode;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service.impl
 * @ClassName: RegisterServiceImpl
 * @Description: 注册服务实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 22:30
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Service
@Transactional
public class RegisterServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements RegisterService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result register(LoginParams loginParams) {
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        String nickname = loginParams.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ) {
            return ResultUtils.fail(false, ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg(), null);
        }
        SysUser sysUser = getUser(account);
        if (sysUser != null) {
            return ResultUtils.fail(false, ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg(), null);
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+ JWTUtils.SIGN));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        /**
         * true:1 false:0
         */
        sysUser.setAdmin(true);
        sysUser.setDeleted(false);
        sysUser.setSalt(JWTUtils.SIGN);
        sysUser.setStatus("");
        sysUser.setEmail("");
        boolean save = this.userService.save(sysUser);
        Result result = null;
        if(save){
            HashMap<String, String> map = new HashMap<>();
            SysUser user = getUser(account);
            map.put("userId",user.getId().toString());
            map.put("userName",user.getAccount());
            String token = JWTUtils.getToken(map, 1);
            // 保存到redis中
            redisTemplate.opsForHash().put("TOKEN",user.getId().toString(), JSONObject.toJSONString(user));
            result = ResultUtils.success(token);
        }else{
            result = ResultUtils.fail(false,ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg(), null);
        }
        return result;
    }

    private SysUser getUser(String account){
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                // 添加用户名
                .eq(SysUser::getAccount, account)
                // 保证只有一条数据
                .last("limit 1");
        return this.userService.getOne(lambdaQueryWrapper);
    }



}
