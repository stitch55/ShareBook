package com.project.sharebook.entities;

import javax.persistence.*;

//关于对书籍的评论的数据表
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //评论序号
    @Column
    private Integer user_id;//那个用户写的评论
    @Column
    private String comment;//评论内容
    @Column
    private Integer book_id; //对那本书的评论
    @Column
    private String book_name;// s书的名字
    @Column
    private String date ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", comment='" + comment + '\'' +
                ", book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
