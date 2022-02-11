package org.lc.my_blog_api.common.cache;

import java.lang.annotation.*;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.common.cache
 * @ClassName: CacheAnnotation
 * @Description: 标注为缓存aop的注解
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 14:10
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
// 元注解
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheAnnotation {


    /**
     * 缓存过期时间
     * @return 过期时间
     */
    long  expire() default 60 * 1000;


    /**
     * 缓存key
     * @return key
     */
    String key() default "";

}
