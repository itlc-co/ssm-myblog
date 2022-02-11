package org.lc.my_blog_admin.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.vo.params
 * @ClassName: PageParams
 * @Description: 分页参数VO实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 16:45
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
