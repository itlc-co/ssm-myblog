package org.lc.my_blog_api.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.lc.my_blog_api.handler.GloBalInterceptorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.config
 * @ClassName: BlogWebConfig
 * @Description: web配置类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/19 22:26
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Configuration
public class BlogWebConfig implements WebMvcConfigurer {

    @Autowired
    private GloBalInterceptorHandler gloBalInterceptorHandler;

    /**
     * 处理fastJson  long类型数据精度损失
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
        serializeConfig.put(Long.class,ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE,ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 配置拦截器
     * @param registry 拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gloBalInterceptorHandler)
                .addPathPatterns("/test")
                .addPathPatterns("/comments/create/change")
                .addPathPatterns("/articles/publish");
    }

    /**
     * 配置跨域
     *
     * @param registry 注册中心
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置 /** 拦截所有请求
        //本地测试 端口不一致 也算跨域 因此允许本机前端页面端口访问
        registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedOrigins("http://120.25.85.226:8080");
    }


}
