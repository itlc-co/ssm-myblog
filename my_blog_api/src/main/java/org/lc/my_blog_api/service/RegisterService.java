package org.lc.my_blog_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.Result;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.service
 * @ClassName: RegisterService
 * @Description: 注册服务类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 22:29
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface RegisterService extends IService<SysUser> {
    /**
     * 注册用户
     * @param loginParams 用户参数
     * @return 结果集
     */
    Result register(LoginParams loginParams);
}
