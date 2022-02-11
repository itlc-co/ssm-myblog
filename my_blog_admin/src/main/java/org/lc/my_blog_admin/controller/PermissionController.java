package org.lc.my_blog_admin.controller;


import org.lc.my_blog_admin.entity.Permission;
import org.lc.my_blog_admin.service.PermissionService;
import org.lc.my_blog_admin.utils.ResultUtils;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.Result;
import org.lc.my_blog_admin.vo.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permissionList")
    public Result permissionList(@RequestBody PageParams pageParams){
        // 调用PermissionService接口实现分页查询
        ResultVO<Permission> permissionResultVO = this.permissionService.pagePermissionList(pageParams);
        Result result = null;
        if(!ObjectUtils.isEmpty(permissionResultVO)){
            result = ResultUtils.success(permissionResultVO);
        }else{
            result = ResultUtils.fail(null);
        }
        return result;
    }


    @PostMapping("/add")
    public Result addPermission(@RequestBody Permission permission){
        // 调用PermissionService接口实现添加权限信息
        int record = this.permissionService.addPermission(permission);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("添加失败!");
        }
        return result;
    }

    @GetMapping("/delete/{permissionId}")
    public Result deletePermission(@PathVariable("permissionId") Long permissionId){
        // 调用PermissionService接口实现删除指定id的权限信息
        int record = this.permissionService.deletePermission(permissionId);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("删除失败!");
        }
        return result;
    }

    @PostMapping("/update")
    public Result updatePermission(@RequestBody Permission permission){
        // 调用PermissionService接口实现修改指定id的权限信息
        int record = this.permissionService.updatePermission(permission);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("修改失败!");
        }
        return result;
    }

}
