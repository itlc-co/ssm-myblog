package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.Result;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service
 * @ClassName: LoginService
 * @Description: loginService接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:48
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface LoginService {
    /**
     * 处理登录请求
     * @param loginParams 登录参数
     * @return 结果集
     */
    Result selectUser(LoginParams loginParams);

    /**
     * 通过token信息获取用户
     * @param token token信息
     * @return 用户实体对象
     */
    SysUser currentUser(String token);

    /**
     * 通过token信息退出登录
     * @param token token信息
     * @return 结果集
     */
    Result logout(String token);
}
