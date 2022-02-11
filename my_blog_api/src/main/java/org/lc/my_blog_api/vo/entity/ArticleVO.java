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
 * @ClassName: ArticleVO
 * @Description: 文章vo数据model vo:前端需要数据实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2022/1/20 0:52
 * @Copyright: (c) 2022 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

//    雪花算法精度
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;

    private String createDate;

    private String author;

    private ArticleBodyVO body;

    private List<TagVO> tags;

    private CategoryVO category;


}
