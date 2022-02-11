package org.lc.my_blog_api.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lc.my_blog_api.vo.entity.CategoryVO;
import org.lc.my_blog_api.vo.entity.TagVO;

import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.params
 * @ClassName: ArticleParams
 * @Description: 文章参数
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 21:14
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArticleParams {
    private Long id;

    private ArticleBodyParams body;

    private CategoryVO category;

    private String summary;

    private List<TagVO> tags;

    private String title;
}
