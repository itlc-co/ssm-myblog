package org.lc.my_blog_api.controller;

import org.lc.my_blog_api.service.RegisterService;
import org.lc.my_blog_api.service.SysUserService;
import org.lc.my_blog_api.vo.params.LoginParams;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.controller
 * @ClassName: RegisterController
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 22:25
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping()
    public Result register(@RequestBody LoginParams loginParams){
        return registerService.register(loginParams);
    }
}
