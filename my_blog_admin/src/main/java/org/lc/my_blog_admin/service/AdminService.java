package org.lc.my_blog_admin.service;

import org.lc.my_blog_admin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_admin.vo.entity.AdminVO;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
public interface AdminService extends IService<Admin> {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    Admin selectUserByName(String username);

    /**
     * 查询所有用户信息
     * @param pageParams 分页参数
     * @return 结果集
     */
    ResultVO<AdminVO> pageAdminList(PageParams pageParams);

    /**
     * 添加用户信息
     * @param admin 用户实体实例
     * @return 操作数
     */
    int addAdmin(Admin admin);
    /**
     * 删除用户信息
     * @param adminId 用户实体实例
     * @return 操作数
     */
    int deleteAdmin(Long adminId);
    /**
     * 修改用户信息
     * @param admin 用户实体实例
     * @return 操作数
     */
    int updateAdmin(Admin admin);
}
