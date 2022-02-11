package org.lc.my_blog_admin.service;

import org.lc.my_blog_admin.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据分页信息查询分页权限数据
     * @param pageParams 分页参数信息
     * @return 权限信息
     */
    ResultVO<Permission> pagePermissionList(PageParams pageParams);

    /**
     * 添加权限数据
     * @param permission 权限实体类实例
     * @return 记录数
     */
    int addPermission(Permission permission);

    /**
     * 删除权限数据信息
     * @param permissionId 权限id
     * @return 记录数
     */
    int deletePermission(Long permissionId);

    /**
     * 修改权限数据信息
     * @param permission 权限实体类实例
     * @return 记录数
     */
    int updatePermission(Permission permission);

    /**
     * 根据所有的权限id查询所有的权限信息
     * @param permissionIds 权限idlist
     * @return  所有的权限信息
     */
    List<Permission> selectPermission(List<Long> permissionIds);
}
