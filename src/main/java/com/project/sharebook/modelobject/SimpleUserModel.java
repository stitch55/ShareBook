package com.project.sharebook.modelobject;

import com.project.sharebook.entities.Comment;
//用户的简单数据模型
//昵称，个性签名  头像  评论
public class SimpleUserModel {
    private  Integer id;

    private String nickname; //昵称
    private String inspiration;//个性签名

    private String photoUrl;//头像
    /*
    在社区中展示的是这个用户最近的一条评论（显示用户的最新活动动）
    在书籍的具体信息中展示的是该用户对这本书的最近的评论
     */
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

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
}
