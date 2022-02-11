package org.lc.my_blog_admin.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.generator
 * @ClassName: MyBatisPlusGenerator
 * @Description: MyBatisPlus代码生成器
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/19 22:42
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class MyBatisPlusGenerator {

    private static final String URL="jdbc:mysql://localhost:3306/ms_blog?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USERNAME="root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {
        codeAutoGenerator();
    }

    public static void codeAutoGenerator() {
        FastAutoGenerator
                .create(URL,USERNAME,PASSWORD)
                // 全局配置
                .globalConfig((scanner,builder) -> {
                    String projectPath = System.getProperty("user.dir");
                    builder
                            // 设置作者
                            .author(scanner.apply("请输入作者名称？"))
                            //开启swagger模式
//                            .enableSwagger()
                            // 覆盖已经生成的文件
                            .fileOverride()
                            .outputDir(projectPath + "/my_blog_admin/src/main/java");
                })
                // 包名配置
                .packageConfig((scanner,builder) -> {
                    String projectPath = System.getProperty("user.dir");
                    builder
                            // 父包名
                            .parent(scanner.apply("请输入包名？"))
                            // 路径信息 OutputFile.mapperXml mapper.xml的输出位置
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath+"/my_blog_admin/src/main/resources/mappers"));
                })
                // 策略配置
                .strategyConfig((scanner,builder)-> {
                    builder
                            // 添加表名
                            .addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                            // 过滤表名前缀
                            .addTablePrefix("ms_")
                            // 开启大写映射
                            .enableCapitalMode()
                            // controller的包策略配置
                            .controllerBuilder()
                            // 驼峰转化
                            .enableHyphenStyle()
                            // 接口类名格式
                            .formatFileName("%sController")
                            // service的包策略配置
                            .serviceBuilder()
                            // 接口类名格式
                            .formatServiceFileName("%sService")
                            // 实现类名格式
                            .formatServiceImplFileName("%sServiceImpl")
                            // 实体类策略配置
                            .entityBuilder()
                            // 开启lombok
                            .enableLombok()
                            // 添加表自动填充字段
                            .addTableFills(new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.INSERT_UPDATE))
                            .build();
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
