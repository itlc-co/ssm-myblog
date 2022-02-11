package org.lc.my_blog_api.controller;


import org.lc.my_blog_api.service.SysUserService;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private SysUserService userService;


    /**
     * 通过token信息获取用户信息
     * @param token token信息
     * @return 用户信息
     */
    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return this.userService.currentUser(token);
    }

}
