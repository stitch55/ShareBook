package com.project.sharebook.service.Imp;

import com.project.sharebook.entities.Comment;
import com.project.sharebook.entities.Focus;
import com.project.sharebook.entities.User;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.error.EmBusinessError;
import com.project.sharebook.modelobject.PersonalCenter_UserModel;
import com.project.sharebook.modelobject.SimpleUserModel;
import com.project.sharebook.modelobject.UserModel;
import com.project.sharebook.repository.BookRepository;
import com.project.sharebook.repository.CommentRepository;
import com.project.sharebook.repository.FocusRepository;
import com.project.sharebook.repository.UserRepository;
import com.project.sharebook.service.UserService;
import javafx.beans.property.SimpleListProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.ExtensionInstallationException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    FocusRepository focusRepository;
   //处理社区的请求，返回最新进行评论的用户
    @Override
    public List<SimpleUserModel> getCommunityData() throws BusinessException {
      //获取当前登录的用户，判断当前是否有用户登录，如果当前有用户登录则在社区中不显示他
        UserModel sessionUser = (UserModel) httpServletRequest.getSession().getAttribute("user");

        List<SimpleUserModel> simpleUserModelList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findAllByOrder();
        //对评论进行排序，使最新的评论排在最前面
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getId()>o2.getId()){
                    return -1;//从大到小排序
                }else {
                    return 0;
                }
            }
        });

//根据评论的id 查找 user
            for (Comment com :commentList) {
                User user = new User();
                try {
                    user = userRepository.getOne(com.getUser_id());
                } catch (Exception ex) {
                    throw new BusinessException(EmBusinessError.USER_FIND_FAIL);
                }
                if (sessionUser==null) {//当前没有用户登录
                    SimpleUserModel model = new SimpleUserModel();
                    BeanUtils.copyProperties(user, model);
                    model.setComment(com);
                    simpleUserModelList.add(model);
                }else {
                     if(user.getId()!=sessionUser.getId()){
                         SimpleUserModel model = new SimpleUserModel();
                         BeanUtils.copyProperties(user, model);
                         model.setComment(com);
                         simpleUserModelList.add(model);
                     }
                }
            }
            //对查询到的用户进行长度的判断
        if(simpleUserModelList.size()>10){
                simpleUserModelList = simpleUserModelList.subList(0,10);
        }
        return simpleUserModelList;
    }
//实现模拟登录，返回用户的基本信息
    @Override
    public UserModel Login(Integer id) throws BusinessException {
        User user = userRepository.getOne(id);
        if(user==null){
            throw  new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
       UserModel model = new UserModel();
        BeanUtils.copyProperties(user,model);
        return model;
    }
//实现模拟注册
    @Override
    public String Register(User user) {
        //先判断数据库中是否已经存在相同昵称的数据
        User SameName = userRepository.getUsrByName(user.getNickname());
        if(SameName==null){
            userRepository.save(user);
            return "success";
        }else {
            return "fail";
        }
    }
//用户修改自己的信息
    @Override
    public void UpdataUser(PersonalCenter_UserModel model) {
        //修改，昵称，密码感悟
        //数据校验
        //转化
        User user = new User();
        BeanUtils.copyProperties(model,user);
        //数据入库
        userRepository.save(user);
    }
//注销账号
    @Transactional
    @Override
    public void Distroy(Integer id) {
        //要删除每个表中与他有关的所有信息
        //tbl_user
//userRepository.deleteById(id);

        //tbl_comment

        //tbl_book

        //btl_focus


    }
//进入个人中心获取用户信息
    @Override
    public PersonalCenter_UserModel PersonalCenter(Integer id) {

        User user = userRepository.getOne(id);
        int bookNum = bookRepository.getBookNumber(id);
        int focusingCount = focusRepository.getFocusingCount(id);
        int fansCount = focusRepository.getFansCount(id);
        PersonalCenter_UserModel userModel = new PersonalCenter_UserModel();
        BeanUtils.copyProperties(user,userModel);
        userModel.setFocusCount(focusingCount);
        userModel.setOwn_count(bookNum);
        userModel.setFansCount(fansCount);

        return userModel;
    }
//修改头像
    @Override
    public String EditPhoto(String url, Integer id) {
        try {
            User user = userRepository.getOne(id);
           user.setPhotoUrl(url);
            userRepository.save(user);
        }catch (Exception ex){
            return "fail";
        }
       return "success";
    }
//获取关注的用户和粉丝
    @Override
    public List<SimpleUserModel> getFocusOrFans(Integer id,int flag) throws BusinessException {
        List<SimpleUserModel> modelList = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if(flag==1){
            ids = focusRepository.getFocus_id(id);
        }else {
            ids=focusRepository.getFans_id(id);
        }
        if(ids.size()!=0){
            //根据用户id 查找 用户
            for(int fd:ids){
                User user = new User();
                try{
                    user = userRepository.getOne(fd);
                }catch (Exception ex){
                    throw new BusinessException(EmBusinessError.USER_FIND_FAIL);
                }
                try {
                    SimpleUserModel model = new SimpleUserModel();
                    BeanUtils.copyProperties(user,model);
                    modelList.add(model);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }

            }
        }
        return modelList;
    }
//取消关注
    @Override
    public String CancelFocus(Integer user_id, Integer focused_id){
        try{
        int id = focusRepository.getId(user_id,focused_id);
        focusRepository.deleteById(id);
        }catch (Exception ex){
           return "fail";
        }
        return "success";
    }
//添加关注关系
    @Override
    public String AddFocus(Integer user_id, Integer fan_id) {
        try{
            //先查找是否已经存在关注关系
            Focus f = focusRepository.getFocusByFocusing_idAndFocused_id(user_id,fan_id);
            if(f!=null){
                return "fail";
            }else {
                Focus focus = new Focus();
                focus.setFocused_id(fan_id);
                focus.setFocusing_id(user_id);
                focusRepository.save(focus);
            }
        }catch (Exception ex){
            return "fail";
        }
        return "success";
    }


}
