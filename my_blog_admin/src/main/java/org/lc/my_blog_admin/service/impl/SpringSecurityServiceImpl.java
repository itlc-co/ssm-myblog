package org.lc.my_blog_admin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lc.my_blog_admin.entity.Admin;
import org.lc.my_blog_admin.service.AdminService;
import org.lc.my_blog_admin.service.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.service.impl
 * @ClassName: SpringSecurityServiceImpl
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 17:52
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Component
@Slf4j
public class SpringSecurityServiceImpl implements SpringSecurityService {


    @Autowired
    private AdminService adminService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //当用户登录的时候，springSecurity 就会将请求 转发到此
        //根据用户名 查找用户，不存在 抛出异常，存在 将用户名，密码，授权列表 组装成springSecurity的User对象 并返回
        log.info("username:{}",username);
        Admin user  = this.adminService.selectUserByName(username);
        if(user != null){
//            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            //剩下的认证 就由框架帮我们完成
            return new User(username,user.getPassword(), new ArrayList<>());
        }
        return null;
    }



}