package org.lc.my_blog_api.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.utils
 * @ClassName: HttpContextUtils
 * @Description: HttpContext工具类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 23:20
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
