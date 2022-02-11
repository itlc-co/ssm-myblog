package org.lc.my_blog_api.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.params
 * @ClassName: ArticleBodyParams
 * @Description: 文章body参数
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 21:15
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBodyParams {

    private String content;

    private String contentHtml;
}
