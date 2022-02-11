package org.lc.my_blog_api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.mapper.SysUserMapper;
import org.lc.my_blog_api.service.LoginService;
import org.lc.my_blog_api.service.SysUserService;
import org.lc.my_blog_api.utils.JWTUtils;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.ErrorCode;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service.impl
 * @ClassName: LoginServiceImpl
 * @Description: 登录service实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:48
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements LoginService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询用户信息校验信息并保存到redis缓存中
     * @param loginParams 登录参数
     * @return 结果集
     */
    @Override
    public Result selectUser(LoginParams loginParams) {
        // 获取参数中的用户名与密码
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return ResultUtils.result(false, ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg(), null);
        }
        String md5Password = DigestUtils.md5Hex(password+JWTUtils.SIGN);
        SysUser user = this.userService.selectUser(account, md5Password);
        // 封装结果集
        Result result = null;
        if (user != null) {
            // 生成token
            HashMap<String, String> map = new HashMap<>();
            // 添加payload信息
            map.put("userId", user.getId().toString());
            map.put("userName", user.getNickname());
            String token = JWTUtils.getToken(map, 1);
            // 返回token
            result = ResultUtils.success(token);
            // 保存到redis中
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            hashOperations.put("TOKEN", user.getId().toString(), JSONObject.toJSONString(user));
        } else {
            // 用户名或者密码错误
            result = ResultUtils.fail(false, ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg(), null);
        }
        return result;
    }

    /**
     * 通过token信息获取 SysUser实例
     * @param token token信息
     * @return SysUser实例
     */
    @Override
    public SysUser currentUser(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        // 获取payload信息
        Map<String, Claim> payLoad = JWTUtils.getPayLoad(token);
        if (payLoad == null) {
            return null;
        }
        // redis中获取tokenjson数据信息
        String userId = payLoad.get("userId").asString();
        String user = (String) redisTemplate.opsForHash().get("TOKEN", userId);
        if (user == null) {
            return null;
        }
        // 解析json字符串返回SysUser实例
        return JSONObject.parseObject(user,SysUser.class);
    }

    /**
     * 注销登录
     * @param token token信息
     * @return 结果集
     */
    @Override
    public Result logout(String token) {
        if (StringUtils.isBlank(token)) {
            // token为空异常
            return ResultUtils.fail(false, ErrorCode.TOKEN_ERROR.getCode(),"token为null",null);
        }
        // 获取payload信息
        Map<String, Claim> payLoad = JWTUtils.getPayLoad(token);
        if (payLoad == null) {
            return ResultUtils.fail(false,ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg(),null);
        }
        // redis中获取tokenjson数据信息
        String userId = payLoad.get("userId").asString();
        redisTemplate.opsForHash().delete("TOKEN",userId);
        return ResultUtils.success(null);
    }


}
