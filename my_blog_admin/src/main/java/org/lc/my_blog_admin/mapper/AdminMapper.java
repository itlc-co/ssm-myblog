package org.lc.my_blog_admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lc.my_blog_admin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lc_co
 * @since 2022-01-22
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
