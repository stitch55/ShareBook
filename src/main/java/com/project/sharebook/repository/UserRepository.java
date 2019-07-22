package com.project.sharebook.repository;

import com.project.sharebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Integer> {
    //注册的时候查找是否存在相同昵称的数据
    @Query(value = "select id,borrow_count,inspiration,nickname,number,own_count,password,photo_url FROM tbl_user where nickname =? ",nativeQuery = true)
    public User getUsrByName(@Param("nickname") String name);
    //根据id 照应胡
    @Query("select u from User u where u.id = id")
    public User getUserByid(@Param("id")Integer id);

}
