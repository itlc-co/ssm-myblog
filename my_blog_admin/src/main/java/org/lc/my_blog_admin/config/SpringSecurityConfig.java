package org.lc.my_blog_admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.config
 * @ClassName: SpringSecurityConfig
 * @Description: 安全组件SpringSecurity配置类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 17:36
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 权限验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //开启登录认证
//                .antMatchers("/user/findAll").hasRole("admin") //访问接口需要admin的角色
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/admin/**")
                //自定义service 来去实现实时的权限认证
                .access("@authService.auth(request,authentication)")
                .antMatchers("/pages/**").authenticated()
                .and().formLogin()
                //自定义的登录页面
                .loginPage("/login.html")
                //登录处理接口
                .loginProcessingUrl("/login")
                //定义登录时的用户名的key 默认为username
                .usernameParameter("username")
                //定义登录时的密码key，默认是password
                .passwordParameter("password")
                .defaultSuccessUrl("/pages/main.html")
                .failureUrl("/login.html")
                //通过 不拦截，更加前面配的路径决定，这是指和登录表单相关的接口 都通过
                .permitAll()
                //退出登录配置
                .and().logout()
                //退出登录接口
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                //退出登录的接口放行
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable() //csrf关闭 如果自定义登录 需要关闭
                // 支持iframe
                .headers().frameOptions().sameOrigin();
    }
}
