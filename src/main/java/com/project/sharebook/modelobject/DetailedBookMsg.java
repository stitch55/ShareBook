package com.project.sharebook.modelobject;

import com.project.sharebook.entities.Book;
import com.project.sharebook.entities.Comment;

import java.util.List;

//书籍详细信息的展示，书本的拥有者（简单的昵称显示）
//在评论区中
public class DetailedBookMsg {
    private SimpleUserModel Owner;
    private List<SimpleUserModel> users; //同时记录评论者和评论（最新评论）
    private BookModel bookModel; //书本的详细信息 ： 书名 封面  作者 点赞数 书籍的状态 ,书本的简介

    public SimpleUserModel getOwner() {
        return Owner;
    }

    public void setOwner(SimpleUserModel owner) {
        Owner = owner;
    }

    public List<SimpleUserModel> getUsers() {
        return users;
    }

    public void setUsers(List<SimpleUserModel> users) {
        this.users = users;
    }

    public BookModel getBookModel() {
        return bookModel;
    }

    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }
}
