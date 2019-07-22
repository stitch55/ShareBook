package com.project.sharebook.controller;



import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.*;
import com.project.sharebook.response.AjaxData;
import com.project.sharebook.service.BookService;
import com.project.sharebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    BookService bookService;

    //用户登录，模拟登陆，实际上是根据id 查找对应的用户
    @GetMapping("/login")
    public String Login(@RequestParam("id") Integer id) throws BusinessException {
    //判断当前用户是否已经登陆
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("user");
        System.out.println(userModel);
        if(userModel==null){
            //根据id 查找
            userModel = userService.Login(id);
            //将数据保存在session 中
            httpServletRequest.getSession().setAttribute("user",userModel);

            //测试用户信息的动态显示
   //         httpServletRequest.getSession().removeAttribute("user");
            System.out.println("未登录");
        }
   //    model.addAttribute("user",userModel);
      //  httpServletRequest.getSession().removeAttribute("user");
        System.out.println("登录");
        return "redirect:/"; //返回首页，页面上方渲染用户数据
    }
    //用户信息修改
    //想要获取当前用户的相关数据
    @GetMapping("/data")
    public  String UpDataBuid(@RequestParam("id") Integer id,Model model) throws BusinessException {
        UserModel userModel = userService.Login(id);
        model.addAttribute("user",userModel);
       return ""; //返回显示当前用信息的页面
    }
   // 退出当前登录
    @GetMapping("/SignOut")
    public String UserOut(@RequestParam("id") Integer id){
        httpServletRequest.getSession().removeAttribute("user");
        System.out.println("退出登录");
        return "redirect:/";
    }
    //注销当前账号。没有测试
    @GetMapping("/delete")
    public String DeUser(@RequestParam("id")Integer id){
        userService.Distroy(id);
        httpServletRequest.getSession().removeAttribute("user");
        System.out.println("注销用户");
        return "redirect:/";
    }
    //进入个人中心
    @GetMapping("/personMsg")
    public String ToPersonalCenter(@RequestParam("id") Integer id,Model model) throws BusinessException {
        System.out.println("进入个人中心");
        //一次性获取这个页面所需要的所有信息
        //所需对的个人信息
        PersonalCenter_UserModel userModel = userService.PersonalCenter(id);
        model.addAttribute("p",userModel); //用户基本信息
        //拥有的书本
         List<BookModel> bookModelList = bookService.GetOwnBooks(id);
         model.addAttribute("books",bookModelList);//用户拥有的书本
        //关注的用户
        List<SimpleUserModel> focus = userService.getFocusOrFans(id,1);
       model.addAttribute("focus",focus); //关注的用户
       //当前用户的粉丝
        List<SimpleUserModel> fans = userService.getFocusOrFans(id,0);

        Map<Integer,SimpleUserModel> faMap = Focus_Fans(fans,focus);

        model.addAttribute("fanMap", faMap);//用户的粉丝  带有是否互关的标志

        return "personal-center";
    }

    private Map<Integer,SimpleUserModel> Focus_Fans(List<SimpleUserModel> fans, List<SimpleUserModel> focus) {
        Map<Integer,SimpleUserModel> faMap = new HashMap<>();
        Integer test = 1+Double.MAX_EXPONENT;
        System.out.println(test);

        //处理互关
        for(int i=0;i<fans.size();i++){
            int flag =0;
            for(int j=0;j<focus.size();j++){
                if(fans.get(i).getId()==focus.get(j).getId()){
                    flag=1;
                }
            }
            if(flag==0){
                faMap.put(Double.MAX_EXPONENT+fans.get(i).getId(),fans.get(i));
            }else {
                faMap.put(fans.get(i).getId(),fans.get(i));
            }
        }
        return faMap;
    }


    //取消/添加关注
    @PostMapping("/Focus")
    public String CancelFocus(@RequestParam("userId")Integer id, @RequestParam("focusId") Integer focus_id,@RequestParam("flag")Integer flag, Model model) throws BusinessException {

        System.out.println("id "+id+" fid "+focus_id);
        if(flag==1){
            userService.CancelFocus(id,focus_id);
        }else {
            userService.AddFocus(id,focus_id);
        }
        List<SimpleUserModel> focus = userService.getFocusOrFans(id,1);
        model.addAttribute("focus",focus); //关注的用户
        //当前用户的粉丝
        List<SimpleUserModel> fans = userService.getFocusOrFans(id,0);
        Map<Integer,SimpleUserModel> faMap = Focus_Fans(fans,focus);

        model.addAttribute("fanMap", faMap);

        return "personal-center::Focus_Fans";
    }

}
