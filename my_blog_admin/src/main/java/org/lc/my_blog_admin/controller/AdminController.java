package org.lc.my_blog_admin.controller;


import org.lc.my_blog_admin.entity.Admin;
import org.lc.my_blog_admin.entity.Permission;
import org.lc.my_blog_admin.service.AdminService;
import org.lc.my_blog_admin.service.PermissionService;
import org.lc.my_blog_admin.utils.ResultUtils;
import org.lc.my_blog_admin.vo.entity.AdminVO;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.Result;
import org.lc.my_blog_admin.vo.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
@RestController()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminList")
    public Result adminList(@RequestBody PageParams pageParams){
        // 调用adminService接口实现分页查询
        ResultVO<AdminVO> adminVOResultVO = this.adminService.pageAdminList(pageParams);
        Result result = null;
        if(!ObjectUtils.isEmpty(adminVOResultVO)){
            result = ResultUtils.success(adminVOResultVO);
        }else{
            result = ResultUtils.fail(null);
        }
        return result;
    }



    @PostMapping("/add")
    public Result addAdmin(@RequestBody Admin admin){
        // 调用adminService接口实现添加用户信息
        int record = this.adminService.addAdmin(admin);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("添加失败!");
        }
        return result;
    }

    @GetMapping("/delete/{permissionId}")
    public Result deleteAdmin(@PathVariable("permissionId") Long adminId){
        // 调用adminService接口实现删除指定id的用户信息
        int record = this.adminService.deleteAdmin(adminId);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("删除失败!");
        }
        return result;
    }

    @PostMapping("/update")
    public Result updateAdmin(@RequestBody Admin admin){
        // 调用adminService接口实现修改指定id的用户信息
        int record = this.adminService.updateAdmin(admin);
        Result result = null;
        if(record > 0){
            result = ResultUtils.success(null);
        }else{
            result = ResultUtils.fail("修改失败!");
        }
        return result;
    }



}
