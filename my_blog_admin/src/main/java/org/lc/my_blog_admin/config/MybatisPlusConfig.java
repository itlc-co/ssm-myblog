package org.lc.my_blog_admin.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_admin.config
 * @ClassName: MybatisPlusConfig
 * @Description: mybatisplus配置类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 16:05
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Configuration
@MapperScan("org.lc.my_blog_admin.mapper")
public class MybatisPlusConfig {

    //分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
