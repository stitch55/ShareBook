package com.project.sharebook.controller;

import com.project.sharebook.entities.Comment;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.response.AjaxData;
import com.project.sharebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentAjaxController {
    @Autowired
    CommentService commentService;
    //添加评论
    @PostMapping("/comment")
    public AjaxData addComment(@RequestBody Comment  comment) throws BusinessException {
        //service 层的方法.添加失败则报错
        System.out.println("添加评论");
       commentService.addComment(comment);
        return AjaxData.create(comment);
    }
}
