package com.project.sharebook.entities;

import javax.persistence.*;

//书籍信息数据表
@Entity
@Table(name = "tbl_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//书籍的序号
    @Column
    private String  book_name; //书籍的名称
    @Column
    private String   author; //作者
    @Column
    private String coverUrl;//封面
//    @Column
//    private String  book_source;//提供的书籍的来源，可以为空
    @Column
    private Integer  user_id;//拥有者的id
//    @Column
//    private Integer  type;//书籍的种类;  一共操作两种1 2
    @Column(columnDefinition="TEXT")
    private String  text;//书籍的概要 //在数据库中的类型设计不能是varchar  存不下，在数据库中的类型设计应该是：text
    @Column
    private Integer status;//书籍现在的状态:0借出1未借出
    @Column
    private Integer zanCount;//书籍获得的点赞数

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

//    public String getBook_source() {
//        return book_source;
//    }
//
//    public void setBook_source(String book_source) {
//        this.book_source = book_source;
//    }

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
        return "Book{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", zanCount=" + zanCount +
                '}';
    }
}
