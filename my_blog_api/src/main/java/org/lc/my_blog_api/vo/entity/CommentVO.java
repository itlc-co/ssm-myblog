package org.lc.my_blog_api.vo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: my_blog
 * @PackageName: org.lc.my_blog_api.vo.entity
 * @ClassName: CommentVO
 * @Description: 评论信息VOModel数据
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/21 17:58
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    //  雪花算法精度
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private UserVO author;

    private String content;

    private List<CommentVO> childrens;

    private String createDate;

    private Integer level;

    private UserVO toUser;
}
