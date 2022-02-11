package org.lc.my_blog_api.controller;

import org.lc.my_blog_api.service.LoginService;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.controller
 * @ClassName: LogoutController
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 22:00
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@RestController
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token){
        return this.loginService.logout(token);
    }

}
