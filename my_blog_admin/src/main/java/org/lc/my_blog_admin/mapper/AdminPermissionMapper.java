package org.lc.my_blog_admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lc.my_blog_admin.entity.AdminPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
@Mapper
public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {

    /**
     * 根据用户id查询所有权限id
     * @param userId 用户id
     * @return 所有权限id
     */
    @Select("SELECT id FROM ms_admin_permission where admin_id = #{userId}")
    List<Long> selectPermissionIdsByUserId(Long userId);

}
