package com.project.sharebook.modelobject;

import com.project.sharebook.entities.BookSource;
import com.project.sharebook.entities.Type;

import java.util.List;

//书籍的详细信息
public class BookModel {
    private Integer id;//书籍的序号

    private String  book_name; //书籍的名称

    private String   author; //作者

    private String coverUrl;//封面

    private Integer  user_id;//拥有者的id


    private String  text;//书籍的概要

    private Integer status;//书籍现在的状态:0借出1未借出

    private Integer zanCount;//书籍获得的点赞数

    private Type type;

    private BookSource bookSource;//书本的获取方式

    public BookSource getBookSource() {
        return bookSource;
    }

    public void setBookSource(BookSource bookSource) {
        this.bookSource = bookSource;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getZanCount() {
        return zanCount;
    }

    public void setZanCount(Integer zanCount) {
        this.zanCount = zanCount;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", zanCount=" + zanCount +
                ", type=" + type +
                ", bookSource=" + bookSource +
                '}';
    }
}
