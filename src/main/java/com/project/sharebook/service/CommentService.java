package com.project.sharebook.service;

import com.project.sharebook.entities.Comment;
import com.project.sharebook.error.BusinessException;

//有关评论的操作
public interface CommentService {
    //添加评论
   public void addComment(Comment com) throws BusinessException;
}
