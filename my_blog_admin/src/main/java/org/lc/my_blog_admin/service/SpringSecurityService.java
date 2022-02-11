package org.lc.my_blog_admin.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.service
 * @ClassName: SpringSecurityService
 * @Description: springSecurityService接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 17:52
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Service
public interface SpringSecurityService extends UserDetailsService {
}
