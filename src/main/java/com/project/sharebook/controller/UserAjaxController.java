package com.project.sharebook.controller;

import com.project.sharebook.entities.User;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.PersonalCenter_UserModel;
import com.project.sharebook.modelobject.SimpleUserModel;
import com.project.sharebook.modelobject.UserModel;
import com.project.sharebook.response.AjaxData;
import com.project.sharebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//处理ajax 请求的Controller必须向页面返回json格式的数据，所以要单独列出来，单独进行处理
//要像前端传递数据就涉及到要对数据进行处理
@RestController
public class UserAjaxController extends BaseController {
    //用户注册
    @Autowired
    UserService userService;
    //用户注册，模拟第三方注册，前端页面自己模拟数据
    @PostMapping("/register")
    public AjaxData Register(@RequestBody User user){
        String state = userService.Register(user);
        return AjaxData.create(user,state);
    }
    //用户信息修改
    //获取页面修改后的数据
    @PostMapping("/userupdata")
    public AjaxData UpData(@RequestBody PersonalCenter_UserModel userModel){
        //调用service
        userService.UpdataUser(userModel);
        //返回数据，成功的提示
        return AjaxData.create(userModel);
    }
    //用户修改头像
    @PostMapping("/eidtphoto")
    public AjaxData EditPhoto(@RequestBody User user){
        String state = userService.EditPhoto(user.getPhotoUrl(),user.getId());
        return AjaxData.create(user,"success");
    }
    //在社区模块的添加关注
    @PostMapping("/communityfocus")
    public AjaxData CommunityFocus(@RequestParam("userId")Integer userId,@RequestParam("focusId")Integer fid){
       String state = userService.AddFocus(userId,fid);
        return AjaxData.create(userId,state);
    }


}
