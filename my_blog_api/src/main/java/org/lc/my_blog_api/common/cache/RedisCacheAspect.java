package org.lc.my_blog_api.common.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.common.cache
 * @ClassName: RedisCacheAspect
 * @Description: redis缓存切面
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 14:09
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 配置通知点
     * 使用指定的注解标注的为通知点
     */
    @Pointcut("@annotation(org.lc.my_blog_api.common.cache.CacheAnnotation)")
    public void pointCut(){
    }


    /**
     * 环绕通知
     * @return
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        // 获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        // 通知点的执行方法名
        String methodName = signature.getName();
        // 参数信息以及类型信息
        String params = "";
        StringBuilder paramsBuild = new StringBuilder();
        Object[] joinPointArgs = proceedingJoinPoint.getArgs();
        Class[] paramsTypes = new Class[joinPointArgs.length];
        int index = 0;
        for (Object pointArg : joinPointArgs) {
            if(pointArg!=null){
                paramsBuild.append(JSON.toJSONString(pointArg));
                paramsTypes[index++] = pointArg.getClass();
            }else{
                paramsTypes[index++] = null;
            }
        }
        // 加密参数信息
        if(StringUtils.isNoneEmpty(paramsBuild)){
            params = DigestUtils.md5Hex(paramsBuild.toString());
        }
        Method method = null;
        try {
            method = proceedingJoinPoint.getSignature().getDeclaringType().getMethod(methodName,paramsTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return ResultUtils.fail(false,999,"系统异常!",null);
        }
        //获取Cache注解
        CacheAnnotation cache = method.getAnnotation(CacheAnnotation.class);
         // 缓存过期时间
        long expire = cache.expire();
        // 缓存的key
        String key = cache.key();
        // 判断是否存在redis缓存中如果存在则直接从redis中取出
        // 拼接rediskey Key
        String redisKey = className+"::"+methodName;
        // hashkey
        String hashKey = key+"::"+params;
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        // 存在对应的key走缓存取数据
        if(hashOperations.hasKey(redisKey,hashKey)){
            log.info("走了缓存,{}---{}",className,methodName);
            return JSON.parseObject(hashOperations.get(redisKey,hashKey),Result.class);
        }else{
            //调用执行的方法查询数据库
            Object proceed = null;
            try {
                proceed = proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
                return ResultUtils.fail(false,999,"系统异常!",null);
            }
            // 将结果集存入redist中
            hashOperations.put(redisKey,hashKey,JSON.toJSONString(proceed));
            log.info("存入缓存{}---{}",className,methodName);
            return proceed;
        }
    }


}
