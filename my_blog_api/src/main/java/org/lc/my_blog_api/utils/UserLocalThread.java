package org.lc.my_blog_api.utils;

import org.lc.my_blog_api.entity.SysUser;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.utils
 * @ClassName: UserLocalThread
 * @Description: 用户本地线程保存用户信息
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 15:04
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserLocalThread {

    private UserLocalThread(){
    }

    private static final ThreadLocal<SysUser> SYS_USER_THREAD_LOCAL = new ThreadLocal<SysUser>();


    public static void put(SysUser user){
        SYS_USER_THREAD_LOCAL.set(user);
    }


    public static SysUser get(){
        return SYS_USER_THREAD_LOCAL.get();
    }


    public static void remove(){
        SYS_USER_THREAD_LOCAL.remove();
    }



}
