package org.lc.my_blog_api.utils;

import org.lc.my_blog_api.vo.result.Result;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.utils
 * @ClassName: ResultUtils
 * @Description: 统一结果集工具类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/19 23:53
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class ResultUtils {


    public  static Result result(boolean success,Integer code,String msg,Object data){
        return new Result(success,code,msg,data);
    }
    /**
     * 成功状态下的结果集
     *
     * @param success 是否成功
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     * @return result对象
     */
    public static Result success(boolean success,int code, String msg, Object data) {
        return result(success,code,msg,data);
    }

    /**
     * 成功状态下固定状态码与信息的结果集
     *
     * @param data 数据
     * @return result对象
     */
    public static Result success(Object data) {
        return success(true,200, "success", data);
    }

    /**
     * 异常状态下的结果集
     * @param success 是否成功
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     * @return result对象
     */
    public static Result fail(boolean success,int code, String msg, Object data) {
        return result(success,code, msg, data);
    }

    /**
     * 异常状态下固定状态码的结果集
     *
     * @param msg  信息
     * @param data 数据
     * @return result对象
     */
    public static Result fail(String msg, Object data) {
        return fail(false,400, msg, data);
    }


    /**
     * 异常状态下固定状态码且不需要返回数据的结果集
     *
     * @param msg 信息
     * @return result对象
     */
    public static Result fail(String msg) {
        return fail(false,400, msg, null);
    }
}
