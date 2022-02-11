package org.lc.my_blog_admin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lc.my_blog_admin.entity.Admin;
import org.lc.my_blog_admin.entity.Permission;
import org.lc.my_blog_admin.service.AdminPermissionService;
import org.lc.my_blog_admin.service.AdminService;
import org.lc.my_blog_admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.service.impl
 * @ClassName: AuthService
 * @Description: 权限认证service
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 21:04
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Service
@Slf4j
public class AuthService {


    @Autowired
    private AdminService adminService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AdminPermissionService adminPermissionService;


    public boolean auth(HttpServletRequest request, Authentication authentication){
        // 获取url
        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        // 未登录无凭证信息
        if(principal == null || "anonymousUser".equals(principal)){
            return false;
        }
        // 获取用户名
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        // 调用service接口
        Admin admin = adminService.selectUserByName(username);
        if(admin == null){
            return false;
        }
        if (admin.getId() == 1){
            //认为是超级管理员
            return true;
        }
        // 查询权限信息
        List<Long> permissionIds = this.adminPermissionService.listPermissionIds(admin.getId());
        List<Permission> permissions = permissionService.selectPermission(permissionIds);
        requestURI = requestURI.split("\\?")[0];
        for (Permission permission : permissions) {
            if (requestURI.equals(permission.getPath())){
                log.info("权限通过");
                return true;
            }
        }
        return false;
    }

}
