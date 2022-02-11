package org.lc.my_blog_api.vo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.entity
 * @ClassName: TagVO
 * @Description: 标签vo数据model
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 0:55
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVO {

    private Long id;
    private String tagName;
    private String avatar;
}
