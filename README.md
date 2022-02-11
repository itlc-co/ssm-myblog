# ssm-myblog

---

ssm整合以及springboot，mybatis-plus等技术实现的个人博客系统后端部分，前端已提供，采取前后分离的方式进行开发

## 技术栈

> 后端 

| 技术                  | 间述                             |
| --------------------- | -------------------------------- |
| springboot            | spring+springmvc以及扩展         |
| mysql                 | 数据库                           |
| redis                 | 缓存中间件                       |
| mybatis，mybatis-plus | java操作数据库插件，替代原始jdbc |
| lombok                | 简化实体类的开发                 |
| jwt                   | 认证与授权的组件                 |
| 等等                  |                                  |

> 前端

| 技术 | 间述               |
| ---- | ------------------ |
| vue  | mvvm模式的前端开发 |
| npm  | 依赖集成           |
| 等等 |                    |

## 快速开始

>idea等开发工具导入项目

![image-20220210235853423](readme.assets/image-20220210235853423.png)

>启动前端

```shell
npm install
npm run dev
```

>启动后端

- 命令行启动则只需要启动jar包即可


```shell
java -jar my_blog_api-0.0.1-SNAPSHOT.jar
```

- 非命令行

![image-20220211000044901](readme.assets/image-20220211000044901.png)

![image-20220211000135772](readme.assets/image-20220211000135772.png)

>配置数据库

- 执行sql文件初始化数据库

```sql
source myblog.sql
```


>测试

![image-20220211000646993](readme.assets/image-20220211000646993.png)

## 感谢

- b站大佬 ==> [码神之路](https://space.bilibili.com/473844125)
- 大佬教学 ==> [教学视频](https://www.bilibili.com/video/BV1Gb4y1d7zb)

==膜拜大佬==

