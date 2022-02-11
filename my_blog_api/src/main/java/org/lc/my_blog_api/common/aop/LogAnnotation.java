package org.lc.my_blog_api.common.aop;

import java.lang.annotation.*;
/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.utils
 * @ClassName: LogAnnotation
 * @Description: 自定义log注解实现切面通知
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 23:20
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
//Type 代表可以放在类上面 Method 代表可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";
}
