package org.lc.my_blog_api.handler;

import lombok.extern.slf4j.Slf4j;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.handler
 * @ClassName: ExceptionHandler
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 16:08
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
// 所有controller的异常处理器  Advice aop实现
@ControllerAdvice
@Slf4j
public class GloBalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result catchException(){
//      log.error("系统异常!");
//      return ResultUtils.result(false,500,"系统异常!",null);
//    }

}
