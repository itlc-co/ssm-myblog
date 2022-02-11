package org.lc.my_blog_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lc.my_blog_admin.entity.Permission;
import org.lc.my_blog_admin.mapper.PermissionMapper;
import org.lc.my_blog_admin.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {




    @Autowired
    private PermissionMapper permissionMapper;



    @Override
    public ResultVO<Permission> pagePermissionList(PageParams pageParams) {
        // 配置分页信息
        Page<Permission> permissionPage = new Page<>();
        permissionPage.setCurrent(pageParams.getCurrentPage());
        permissionPage.setSize(pageParams.getPageSize());
        // 配置查询条件
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(pageParams.getQueryString()!=null){
            permissionLambdaQueryWrapper.eq(Permission::getName,pageParams.getQueryString());
        }
        // 调用mapper接口
        Page<Permission> permissionPageRecord = this.permissionMapper.selectPage(permissionPage, permissionLambdaQueryWrapper);
        // 获取分页对象中的记录数list
        List<Permission> permissionPageRecordRecords = permissionPageRecord.getRecords();
        // 封装结果vo对象
        ResultVO<Permission> permissionResultVO = new ResultVO<>();
        // 设置list为记录数list以及total数量
        permissionResultVO.setList(permissionPageRecordRecords);
        permissionResultVO.setTotal(permissionPageRecord.getTotal());
        return permissionResultVO;
    }

    /**
     * 添加权限数据信息
     * @param permission 权限实体类实例
     * @return 记录数
     */
    @Override
    public int addPermission(Permission permission) {
        return this.permissionMapper.insert(permission);
    }


    /**
     * 删除指定id的权限信息
     * @param permissionId 权限id
     * @return
     */
    @Override
    public int deletePermission(Long permissionId) {
        return this.permissionMapper.deleteById(permissionId);
    }


    /**
     * 修改权限信息
     * @param permission 权限实体类实例
     * @return
     */
    @Override
    public int updatePermission(Permission permission) {
        return this.permissionMapper.updateById(permission);
    }


    /**
     * 根据所有的权限id查询所有的权限信息
     * @param permissionIds 权限idlist
     * @return
     */
    @Override
    public List<Permission> selectPermission(List<Long> permissionIds) {
        return this.permissionMapper.selectBatchIds(permissionIds);
    }
}
