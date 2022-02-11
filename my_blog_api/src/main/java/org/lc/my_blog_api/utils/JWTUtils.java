package org.lc.my_blog_api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.utils
 * @ClassName: JWTUtils
 * @Description: JWT工具类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 17:20
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class JWTUtils {

    public static final String SIGN = "&@ADF)R!*(";

    /**
     * @Description: 获取token
     * @Author: lc_co
     * @Date: 2022-01-16 19:11:09
     * @Param: String payLoadMap  payload的内容
     * @Param: String> expiresDate 过期时间以天单位
     * @Return: java.lang.String token信息
     */
    public static String getToken(Map<String, String> payLoadMap, int expiresDate) {
        // 设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, expiresDate);
        // 创建jwt对象
        JWTCreator.Builder builder = JWT.create();

        // 设置payload内容
        payLoadMap.forEach(builder::withClaim);
        String token = builder
                // 设置签发时间
                .withIssuedAt(new Date())
                .withExpiresAt(calendar.getTime())
                // 设置签名算法并配置密钥
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }


    /**
     * @Description: 解码token并且校验
     * @Author: lc_co
     * @Date: 2022-01-16 19:15:00
     * @Param: String token 待校验的token
     * @Return: com.auth0.jwt.interfaces.DecodedJWT  解码后的token
     */
    public static DecodedJWT decodedJWT(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     *
     * @Description: 获取token中的payload信息
     * @Author: lc_co
     * @Date: 2022-01-16 19:18:20
     * @Param: String token  待获取信息的token
     * @Return: java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>  Claim信息接口实例Map
     */
    public  static Map<String, Claim> getPayLoad(String token){
        return decodedJWT(token).getClaims();
    }

}
