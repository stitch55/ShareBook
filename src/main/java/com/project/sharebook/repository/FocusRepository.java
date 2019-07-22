package com.project.sharebook.repository;

import com.project.sharebook.entities.Focus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FocusRepository extends JpaRepository<Focus,Integer> {
    //统计当前用户关注的数量
    @Query("select count(f) from Focus f where f.focusing_id = :id")
    public Integer getFocusingCount(@Param("id") Integer id);
//    //获取当前用户粉丝的数量
    @Query(value = "select count(f) from  Focus f where f.focused_id = :id ")
     public Integer getFansCount(@Param("id") Integer id);

    //获取当前用户所关注的所有用户的Id
    @Query("select f.focused_id from Focus f where f.focusing_id =:id" )
    List<Integer> getFocus_id(@Param("id" ) Integer id);

    //获取当前用户所有粉丝的id
    @Query("select f.focusing_id from Focus f where f.focused_id =:id" )
    List<Integer> getFans_id(@Param("id" ) Integer id);

    //删除当前关注关系
    @Query("select f.id from Focus f where f.focusing_id =:id and f.focused_id =:fid")
    public Integer getId(@Param("id")Integer id,@Param("fid") Integer fid);
    //查找当前是否已经存在该关注关系
    @Query("select f from Focus  f where f.focusing_id = :id and f.focused_id=:fid")
    public Focus getFocusByFocusing_idAndFocused_id(@Param("id")Integer id,@Param("fid")Integer fid);


}
