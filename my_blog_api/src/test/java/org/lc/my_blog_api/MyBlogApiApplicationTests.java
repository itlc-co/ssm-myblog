package org.lc.my_blog_api;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.auth0.jwt.JWT;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.lc.my_blog_api.utils.JWTUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class MyBlogApiApplicationTests {
    String token = "";
    @Test
    void contextLoads() {
    }

    @Test
    void createToken(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", UUID.randomUUID().toString());
        map.put("userName","lc_co");
        token = JWTUtils.getToken(map, 1);
        System.out.println(token);
    }

    @Test
    void  test(){
        String md5Password = DigestUtils.md5Hex("licheng1223");
        System.out.println(md5Password);
    }

}
