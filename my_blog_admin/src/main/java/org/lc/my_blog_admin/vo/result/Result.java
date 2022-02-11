package org.lc.my_blog_admin.vo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.vo.result
 * @ClassName: Result
 * @Description: 统一结果集封装
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 16:34
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private boolean success;
    private Integer code;
    private String msg;
    private Object data;
}
