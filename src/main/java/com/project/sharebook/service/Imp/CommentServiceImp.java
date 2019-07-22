package com.project.sharebook.service.Imp;

import com.project.sharebook.entities.Comment;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.error.EmBusinessError;
import com.project.sharebook.repository.CommentRepository;
import com.project.sharebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    //添加评论
    @Override
    public void addComment(Comment com) throws BusinessException {
        try{
            System.out.println(com);
            commentRepository.save(com);
        }catch (Exception ex){
            throw  new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        }

    }
}
