package org.lc.my_blog_api.service;

import org.lc.my_blog_api.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lc.my_blog_api.vo.entity.CategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
public interface CategoryService extends IService<Category> {
    /**
     * 根据类别id获取类别信息
     * @param categoryId 类别id
     * @return 类别信息VOModel数据
     */
    CategoryVO selectCategory(Long categoryId);

    /**
     *
     * @return 获取所有类型信息
     */
    List<CategoryVO> listAllCategorys();

    /**
     * 根据类别id获取类别信息
     * @param categoryId
     * @return
     */
    CategoryVO categoryDetailsById(Long categoryId);
}
