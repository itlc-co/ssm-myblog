package org.lc.my_blog_api.controller;

import org.lc.my_blog_api.service.LoginService;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.controller
 * @ClassName: LoginController
 * @Description: 登录controller
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:47
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 处理登录请求
     * @param loginParams 登录请求
     * @return 结果集
     */
    @PostMapping()
    public Result login(@RequestBody LoginParams loginParams){
        return loginService.selectUser(loginParams);
    }


}
