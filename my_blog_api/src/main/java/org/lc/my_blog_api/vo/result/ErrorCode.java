package org.lc.my_blog_api.vo.result;
/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.controller
 * @ClassName: LoginController
 * @Description: error异常码枚举
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:47
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public enum ErrorCode {
 PARAMS_ERROR(10001,"参数有误"),
 SYSTEM_ERROR(500,"系统异常"),
 ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
 ACCOUNT_EXIST(10004,"账号已存在"),
 TOKEN_ERROR(10003,"错误的token信息"),
 NO_PERMISSION(70001,"无访问权限"),
 SESSION_TIME_OUT(90001,"会话超时"),

 NO_LOGIN(90002,"未登录"),;

 private int code;
 private String msg;

 ErrorCode(int code, String msg){
  this.code = code;
  this.msg = msg;
 }

 public int getCode() {
  return code;
 }

 public void setCode(int code) {
  this.code = code;
 }

 public String getMsg() {
  return msg;
 }

 public void setMsg(String msg) {
  this.msg = msg;
 }
}
