package org.lc.my_blog_api.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.config
 * @ClassName: MybatisPlusConfig
 * @Description: mybatis-plus配置类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/19 22:21
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Configuration
//扫包，将此包下的接口生成代理实现类，并且注入到spring容器中
//@MapperScan("org.lc.my_blog_api.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }


}
