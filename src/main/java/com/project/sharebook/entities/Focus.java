package com.project.sharebook.entities;

import javax.persistence.*;

//记录用户之间关注关系的数据表

/**
 * 用户之间的而关注关系只有三种
 * 没有关注该系：不会出现在这张数据表中；单一关注关系和互关
 * 单一关注关系：1 互关2
 * 每次录入新的关注关系的时候，将关注者和被关注者交换查询，如果在数据库中存在交换查询的关注关系就直接改变关系为互关
 * 如果没有交换查询的关系就创建新的关注关系
 */
@Entity
@Table(name = "tbl_focus")
public class Focus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;//数据序列
    @Column
    private   Integer focusing_id;//关注者
//    @Column
//    private Integer relationship;//关系1单向关注，2互关
    //取消关注关系的状态记录，数据库中只记录单一的谁关注了谁

    @Column
    private  Integer  focused_id;//被关注者

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFocusing_id() {
        return focusing_id;
    }

    public void setFocusing_id(Integer focusing_id) {
        this.focusing_id = focusing_id;
    }

    public Integer getFocused_id() {
        return focused_id;
    }

    public void setFocused_id(Integer focused_id) {
        this.focused_id = focused_id;
    }
}
