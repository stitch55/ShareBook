package com.project.sharebook.repository;
//对书本的获取方式进行存储

import com.project.sharebook.entities.BookSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookSourceRepository extends JpaRepository<BookSource,Integer> {
    //根据书本的id 获取书本的获取方式
    @Query("select bs from BookSource  bs where bs.book_id=:id" )
    public BookSource getBookSource(@Param("id")Integer id);
}
