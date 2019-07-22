package com.project.sharebook.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer book_id;
    @Column
    private Integer type1;//小说
    @Column
    private Integer type2;//科技教育
    @Column
    private Integer type3;//文艺
    @Column
    private Integer type4;//历史文化
    @Column
    private Integer type5;//经济管理

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

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public Integer getType2() {
        return type2;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }

    public Integer getType3() {
        return type3;
    }

    public void setType3(Integer type3) {
        this.type3 = type3;
    }

    public Integer getType4() {
        return type4;
    }

    public void setType4(Integer type4) {
        this.type4 = type4;
    }

    public Integer getType5() {
        return type5;
    }

    public void setType5(Integer type5) {
        this.type5 = type5;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", type1=" + type1 +
                ", type2=" + type2 +
                ", type3=" + type3 +
                ", type4=" + type4 +
                ", type5=" + type5 +
                '}';
    }
}
