package com.project.sharebook.modelobject;
//搜索页面的数据模型
public class SearchBookModel {
    private Integer id;//书籍的序号

    private String  book_name; //书籍的名称

    private String   author; //作者

    private String coverUrl;//封面

    private String  text;//书籍的概要
    private SimpleUserModel user;//书本的拥有者的基本信息

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SimpleUserModel getUser() {
        return user;
    }

    public void setUser(SimpleUserModel user) {
        this.user = user;
    }
}
