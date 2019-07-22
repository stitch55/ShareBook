package com.project.sharebook.entities;

import javax.persistence.*;

//存储属名和封面
@Entity
@Table(name = "tbl_bookcover")
public class Book_cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//数据序列
    @Column
    private String coverUrl;//封面
    @Column
    private String book_name;//书名
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return coverUrl;
    }

    public void setCover(String cover) {
        this.coverUrl = cover;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}