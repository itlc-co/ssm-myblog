package org.lc.my_blog_api.entity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.entity.dos
 * @ClassName: ArchiveArticles
 * @Description: 归档年月日数量数据dos实例
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:06
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveArticles {
    private Integer year;
    private Integer month;
    private Integer count;

}
