package org.lc.my_blog_api.controller;

import org.apache.commons.lang3.StringUtils;
import org.lc.my_blog_api.utils.QiniuUtils;
import org.lc.my_blog_api.utils.ResultUtils;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.controller
 * @ClassName: ImagesController
 * @Description: 图片上次控制器
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/22 0:25
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/upload")
public class ImagesController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping()
    public Result uploadImages(@RequestParam("image")MultipartFile file){
        // 原始图片文件名
        String originalFilename = file.getOriginalFilename();
        // 上传的图片文件名
        String fileName = UUID.randomUUID().toString() + "."+ StringUtils.substringAfterLast(originalFilename,".");
        // 调用工具类中的上传文件到七牛云中
        boolean upload = qiniuUtils.upload(file, fileName);
        // 结果集封装
        Result result = null;
        if (upload) {
            // 上传成功返回图片url
            result = ResultUtils.success(QiniuUtils.url+"/"+fileName);
        }else{
            result = ResultUtils.fail(false,20001,"上传失败",null);
        }
        return result;
    }


}
