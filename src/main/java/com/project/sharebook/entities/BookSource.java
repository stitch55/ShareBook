package com.project.sharebook.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

//书本的获取方式
@Entity
@Table(name = "tbl_booksource")
public class BookSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer book_id;
    @Column
    private String wangpan;
    @Column
    private String xianxia;

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWangpan() {
        return wangpan;
    }

    public void setWangpan(String wangpan) {
        this.wangpan = wangpan;
    }

    public String getXianxia() {
        return xianxia;
    }

    public void setXianxia(String xianxia) {
        this.xianxia = xianxia;
    }

    @Override
    public String toString() {
        return "BookSource{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", wangpan='" + wangpan + '\'' +
                ", xianxia='" + xianxia + '\'' +
                '}';
    }
}
