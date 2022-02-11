package org.lc.my_blog_admin.service.impl;

import org.lc.my_blog_admin.entity.AdminPermission;
import org.lc.my_blog_admin.mapper.AdminPermissionMapper;
import org.lc.my_blog_admin.service.AdminPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AdminPermissionServiceImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements AdminPermissionService {

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    /**
     * 根据用户id查询权限id
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Long> listPermissionIds(Long userId) {
       return adminPermissionMapper.selectPermissionIdsByUserId(userId);
    }
}
