package org.lc.my_blog_api.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.params
 * @ClassName: LoginParams
 * @Description: 登录参数vo实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:50
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParams {

    private String account;
    private String password;
    private String nickname;

}
