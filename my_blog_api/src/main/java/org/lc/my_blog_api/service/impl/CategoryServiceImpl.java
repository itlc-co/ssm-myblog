package org.lc.my_blog_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.lc.my_blog_api.entity.Category;
import org.lc.my_blog_api.mapper.CategoryMapper;
import org.lc.my_blog_api.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.entity.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param categoryId 类别id
     * @return
     */
    @Override
    public CategoryVO selectCategory(Long categoryId) {
        Category category = this.categoryMapper.selectById(categoryId);
        return castCategoryVO(category);
    }

    /**
     *
     * @return 返回所有类型信息list
     */
    @Override
    public List<CategoryVO> listAllCategorys() {
        List<Category> categories = this.categoryMapper.selectList(new QueryWrapper<>());
        return castCategoryVO(categories);
    }

    /**
     * 获取指定类别id的类型信息
     * @param categoryId
     * @return
     */
    @Override
    public CategoryVO categoryDetailsById(Long categoryId) {
        Category category = this.categoryMapper.selectById(categoryId);
        return castCategoryVO(category);
    }

    /**
     * 批量转换
     * @param categories 实体类实例list
     * @return
     */
    private List<CategoryVO> castCategoryVO(List<Category> categories) {
        ArrayList<CategoryVO> categoryVOS = new ArrayList<>();
        categories.forEach(categorie ->{
            CategoryVO categoryVO = this.castCategoryVO(categorie);
            categoryVOS.add(categoryVO);
        });
        return categoryVOS;
    }

    /**
     * 将Category Mapper下的实体类转换为VOModel数据
     * @param category
     * @return
     */
    private CategoryVO castCategoryVO(Category category) {
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category,categoryVO);
        return categoryVO;
    }



}
