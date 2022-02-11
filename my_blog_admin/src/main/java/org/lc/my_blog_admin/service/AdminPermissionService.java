package org.lc.my_blog_admin.service;

import org.lc.my_blog_admin.entity.AdminPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
public interface AdminPermissionService extends IService<AdminPermission> {

    /**
     * 根据用户id查询所有权限id
     * @param userId 用户id
     * @return 权限ids
     */
    List<Long> listPermissionIds(Long userId);
}
