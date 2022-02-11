package org.lc.my_blog_admin.vo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.vo.entity
 * @ClassName: AdminVO
 * @Description: 用户voModel数据实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/23 18:09
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {

    private Long id;
    private String username;
}
