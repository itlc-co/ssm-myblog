package org.lc.my_blog_api.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.lc.my_blog_api.entity.SysUser;
import org.lc.my_blog_api.service.LoginService;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.utils.UserLocalThread;
import org.lc.my_blog_api.vo.result.ErrorCode;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.handler
 * @ClassName: GloBalInterceptorHandler
 * @Description:  全局拦截器判断是否登录
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 0:53
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Component
public class GloBalInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不为HandlerMethod类型的请求则放行比如静态资源
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        // request中获取token
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            // token为null时
            failResponse(response);
            return false;
//            Result result = ResultUtils.fail(false, ErrorCode.NO_LOGIN.getCode(), "未登录",null);
//            // 设置响应流为json数据
//            response.setContentType("application/json;charset=utf-8");
//            // 将结果集转换为json数据
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
        }
        // 调用loginService接口查询当前token中对应的用户信息是否存在redis中
        SysUser user = this.loginService.currentUser(token);
        if (user==null){
            // user为null时
            failResponse(response);
            return false;
//            Result result = ResultUtils.fail(false, ErrorCode.NO_LOGIN.getCode(), "未登录",null);
//            // 设置响应流为json数据
//            response.setContentType("application/json;charset=utf-8");
//            // 将结果集转换为json数据
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
        }
        // 登录验证成功，放行,我希望在controller中 直接获取用户的信息 怎么获取?放入当前用户的所属线程中
        UserLocalThread.put(user);
        // 登录状态放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除线程中的用户信息，防止内存泄露(弱引用，key回收，value未被回收)
        UserLocalThread.remove();
    }

    /**
     * 未登录异常的响应结果
     * @param response
     * @return
     * @throws IOException
     */
    private void failResponse(HttpServletResponse response) throws IOException {
        Result result = ResultUtils.fail(false, ErrorCode.NO_LOGIN.getCode(), "未登录",null);
        // 设置响应流为json数据
        response.setContentType("application/json;charset=utf-8");
        // 将结果集转换为json数据
        response.getWriter().print(JSON.toJSONString(result));
    }
}
