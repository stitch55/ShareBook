package com.project.sharebook.service;

import com.project.sharebook.entities.User;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.PersonalCenter_UserModel;
import com.project.sharebook.modelobject.SimpleUserModel;
import com.project.sharebook.modelobject.UserModel;

import java.util.List;

public interface UserService {
    //获取所有的用户信息
    public List<SimpleUserModel> getCommunityData() throws BusinessException;
    //模拟登陆，根据id 查找用户
    public UserModel Login(Integer id) throws BusinessException;
    //用户模拟注册
    public String Register(User user);
    //用户修改数据
    public void UpdataUser(PersonalCenter_UserModel model);

    //注销账号
    public void Distroy(Integer id);

    //进入个人中心，获取用户自己的详细信息
    public PersonalCenter_UserModel PersonalCenter(Integer id);

    //修改个人头像
    public String EditPhoto(String url,Integer id);

    //获取当前关注的用户或者粉丝
    public List<SimpleUserModel> getFocusOrFans(Integer id,int flag) throws BusinessException;

    //删除当前的关注关系
    public String CancelFocus(Integer user_id,Integer focused_id) ;
    //添加关注关系
    public String AddFocus(Integer user_id,Integer focused_id) ;
}
