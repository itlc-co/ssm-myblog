package org.lc.my_blog_api.vo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.entity
 * @ClassName: ArticleBodyVO
 * @Description: 文章bodyVoModel实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 15:46
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBodyVO {
    private String content;
}
