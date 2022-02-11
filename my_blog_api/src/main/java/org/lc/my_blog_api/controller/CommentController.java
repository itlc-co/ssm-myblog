package org.lc.my_blog_api.controller;


import org.lc.my_blog_api.service.CommentService;
import org.lc.my_blog_api.vo.params.CommentParams;
import org.lc.my_blog_api.vo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lc_co
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{articleId}")
    public Result commentsArticle(@PathVariable("articleId") Long articleId){
        return commentService.selectCommentsArticle(articleId);
    }

    @PostMapping("/create/change")
    public Result addComment(@RequestBody CommentParams commentParams){
        return this.commentService.addComment(commentParams);
    }



}
