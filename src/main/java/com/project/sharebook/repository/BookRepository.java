package com.project.sharebook.repository;

import com.project.sharebook.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    //根据用户的id 获取她所拥有的所有书本的基本信息
    @Query(value = "select b from Book b where b.user_id =:id")
    List<Book> getAllByUserId(@Param("id")Integer id);

    //根据用户的id 统计拥有的书本的数量
    @Query("select count (b) from Book b where b.user_id =:id")
    Integer getBookNumber(@Param("id") Integer id);

    //根据当前书本的名字和user_id 查找是否该本书已经存在
    @Query("select b from Book b where b.book_name=:name and b.user_id =:id")
    Book getByUseridAndBook_name(@Param("name")String name,@Param("id")Integer id);

    //根据作者的名字搜索书本
    @Query("select b from Book b where b.author = :name")
    List<Book> getBookByAuthor(@Param("name")String name);
    //对书的名字进行精确查询
    @Query("select b from Book b where b.book_name = :name" )
    List<Book> getBookByBook_name(@Param("name")String txt);
    //根据书本的名字进行模糊查询,
    @Query("select b from Book b where b.book_name like concat('%',:key,'%') ")
    List<Book> getBookByBook_nameByLike(@Param("key")String txt);


}
