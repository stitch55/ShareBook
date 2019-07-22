package com.project.sharebook.repository;

import com.project.sharebook.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    //根据用户id 获取最新的评论在查询的时候根据id 进行排序
    @Query(value = "select id,book_id,book_name,comment,date,user_id from tbl_comment WHERE user_id =? order by id",nativeQuery = true)
    public List<Comment> getByUser_idOrder(@Param("id") Integer id);
    //根据书本的id 获取这本书的最新评论
    @Query(value = "select id,book_id,book_name,comment,date,user_id from tbl_comment WHERE book_id =? order by id",nativeQuery = true)
    public List<Comment> getByBookIdOrder(@Param("id") Integer id);
    //删除用户在该表中的所有数据
    @Modifying
    @Query(value = "delete id,book_id,book_name,comment,date,user_id from tbl_comment WHERE user_id =? ",nativeQuery = true)
    void deleteByUserId(@Param("user_id") Integer id);
    //获取最新评论
    @Query(value = "select id,book_id,book_name,comment,date,user_id from tbl_comment order by id",nativeQuery = true)
    List<Comment> findAllByOrder();

    //根据用户和书本查询到comment
    @Query("select c from Comment c where c.user_id=:id and c.book_id=:b_id")
    List<Comment> getComment(@Param("id")Integer user_id,@Param("b_id")Integer b_id);

}
