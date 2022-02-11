package org.lc.my_blog_admin.vo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.vo.result
 * @ClassName: ResultVO
 * @Description: 结果集vo实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 16:38
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    private List<T> list;
    private Long total;
}
