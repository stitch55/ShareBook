package com.project.sharebook.entities;

import javax.persistence.*;

//用户表数据
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;  //自增主键
    @Column
    private String nickname; //昵称
    @Column
    private String number;//账号
    @Column
    private String password;//密码
    @Column
    private String inspiration;//感悟
    @Column
    private String photoUrl;//图片
    @Column
    private Integer own_count;//拥有的数量
    @Column
    private Integer borrow_count;//借阅的数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInspiration() {
        return inspiration;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getOwn_count() {
        return own_count;
    }

    public void setOwn_count(Integer own_count) {
        this.own_count = own_count;
    }

    public Integer getBorrow_count() {
        return borrow_count;
    }

    public void setBorrow_count(Integer borrow_count) {
        this.borrow_count = borrow_count;
    }
}
