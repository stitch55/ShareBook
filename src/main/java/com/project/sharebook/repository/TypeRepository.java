package com.project.sharebook.repository;

import com.project.sharebook.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeRepository extends JpaRepository<Type,Integer> {
    //根据book 的id 查找出 书本的类型信息
    @Query("select t from Type t where t.book_id =:id" )
    public Type getType(@Param("id")Integer id);

    //根据书本的类型查找书本
    //查找type1
    @Query("select t from Type t where t.type1=1")
    public List<Type> getType1();
    //查找type2
    @Query("select t from Type t where t.type2=1")
    public List<Type> getType2();
    //查找type3
    @Query("select t from Type t where t.type3=1")
    public List<Type> getType3();
    //查找type4
    @Query("select t from Type t where t.type4=1")
    public List<Type> getType4();
    //查找type5
    @Query("select t from Type t where t.type5=1")
    public List<Type> getType5();

    //分页查询
    //type1
    @Query(value = "select id,book_id,type1,type2,type3,type4,type5 from tbl_type where type1 =1 limit ?1,?2",nativeQuery = true)
    List<Type> getMorePageBooksByType1(Integer start,Integer count);
    //type2
    @Query(value = "select id,book_id,type1,type2,type3,type4,type5 from tbl_type where type2 =1 limit ?1,?2",nativeQuery = true)
    List<Type> getMorePageBooksByType2(Integer start,Integer count);
    //type3
    @Query(value = "select id,book_id,type1,type2,type3,type4,type5 from tbl_type where type3 =1 limit ?1,?2",nativeQuery = true)
    List<Type> getMorePageBooksByType3(Integer start,Integer count);
    //type4
    @Query(value = "select id,book_id,type1,type2,type3,type4,type5 from tbl_type where type4 =1 limit ?1,?2",nativeQuery = true)
    List<Type> getMorePageBooksByType4(Integer start,Integer count);
    //type5
    @Query(value = "select id,book_id,type1,type2,type3,type4,type5 from tbl_type where type5 =1 limit ?1,?2",nativeQuery = true)
    List<Type> getMorePageBooksByType5(Integer start,Integer count);

}
