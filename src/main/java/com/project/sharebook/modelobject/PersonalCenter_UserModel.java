package com.project.sharebook.modelobject;

import java.util.List;

//个人管理中心的个人信息model
public class PersonalCenter_UserModel {
    private  Integer id;  //自增主键

    private String nickname; //昵称

    private String number;//账号

    private String password;//密码

    private String inspiration;//感悟

    private String photoUrl;//图片

    private Integer own_count;//拥有的数量

    private Integer borrow_count;//借阅的数量

    private Integer focusCount; //关注的数量

    private Integer fansCount;//粉丝的数量
    //暂时在这个模型中不获取关注和被关注的人的具体信息

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

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }
}
