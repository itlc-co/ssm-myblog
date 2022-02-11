package org.lc.my_blog_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.lc.my_blog_admin.entity.Admin;
import org.lc.my_blog_admin.mapper.AdminMapper;
import org.lc.my_blog_admin.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_admin.vo.entity.AdminVO;
import org.lc.my_blog_admin.vo.params.PageParams;
import org.lc.my_blog_admin.vo.result.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {



    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询用户信息
     * @param username 用户名
     * @return
     */
    @Override
    public Admin selectUserByName(String username) {
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getUsername,username)
                .last("limit 1");
        return this.adminMapper.selectOne(adminLambdaQueryWrapper);
    }

    /**
     * 分页查询所有用户信息
     * @param pageParams 分页参数
     * @return
     */
    @Override
    public ResultVO<AdminVO> pageAdminList(PageParams pageParams) {
        // 设置分页信息
        Page<Admin> adminPage = new Page<>(pageParams.getCurrentPage(),pageParams.getPageSize());
        // 设置查询的字段
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.select(Admin::getUsername,Admin::getId);
        // 调用mapper接口
        Page<Admin> adminPages = this.adminMapper.selectPage(adminPage, adminLambdaQueryWrapper);
        // 获取记录
        List<Admin> adminList = adminPages.getRecords();
        // 封装结果集
        ResultVO<AdminVO> adminVOResultVO = new ResultVO<>();
        adminVOResultVO.setList(castAdminVO(adminList));
        adminVOResultVO.setTotal(adminPages.getTotal());
        return adminVOResultVO;
    }


    /**
     * 添加用户信息
     * @param admin 用户实体实例
     * @return
     */
    @Override
    public int addAdmin(Admin admin) {
        String password = admin.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        admin.setPassword(encodePassword);
        return this.adminMapper.insert(admin);
    }

    @Override
    public int deleteAdmin(Long adminId) {
        return this.adminMapper.deleteById(adminId);
    }

    @Override
    public int updateAdmin(Admin admin) {
        String password = admin.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        admin.setPassword(encodePassword);
        return this.adminMapper.updateById(admin);
    }

    /**
     * mapper实体list与vo实体list转换
     * @param adminList
     * @return
     */
    private List<AdminVO> castAdminVO(List<Admin> adminList) {
        ArrayList<AdminVO> adminVOS = new ArrayList<>();
        adminList.forEach(admin -> {
            adminVOS.add(castAdminVO(admin));
        });
        return adminVOS;
    }

    /**
     * mapper实体对象与vo实体对象转换
     * @param admin
     * @return
     */
    private AdminVO castAdminVO(Admin admin) {
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(admin,adminVO);
        return adminVO;
    }
}
