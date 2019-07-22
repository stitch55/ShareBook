package com.project.sharebook.controller;

import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.SimpleUserModel;
import com.project.sharebook.modelobject.SimpleBookModel;
import com.project.sharebook.service.BookService;
import com.project.sharebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//处理非用户首页请求
@Controller
public class HomePageController extends BaseController{
    //返回页面所需的书籍基本信息，封面 名字 作者,控制返回数量，每类书籍4本
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    //返回首页所需要的信息，每类图书需要九本，一共五类
    @GetMapping("/")
    public String HomePageImg(Model model) throws BusinessException {
        for(int i=1;i<6;i++){
            List<SimpleBookModel> bookList = bookService.getBooksEachType(i);
            model.addAttribute("type"+i,bookList);
            Integer countByType = bookService.getBooksCountByType(i);
            //获得数据库中该类的数量
            model.addAttribute("typeLen"+i,countByType);
            System.out.println("书本对的数量："+countByType);
        }
        return "ShareBook";
    }
    //社区请求;返回页面的基本信息，头像，昵称，账号，个性签名，最近的一个评论（动态）
    @GetMapping("/community")
    public String getCommunityUser(Model model) throws BusinessException {
       List<SimpleUserModel> modelList = userService.getCommunityData();

       model.addAttribute("communityData",modelList);
       return "Community";
    }
    @GetMapping("/getMoreBook")
    public String getMoreBook(@RequestParam("type")Integer type,@RequestParam("page")Integer page,Model model){
        //获取返回的书本信息
        List<SimpleBookModel> simpleBookModelList = bookService.getMoreBookOnePage(type,page);
        //获取一共的页数
        Integer pageCount = bookService.getPageCount(type);
        //返回的类型
        String[] types ={"小说","科技教育","文艺","历史文化","经济/教育"};
        String[] typesE ={"NOVEL","SCIENCE&TECHNOLOGY EDUCATION","LITERATURE","HISTORY AND CULTURE","HCONOMIC MANAGEMENT"};
        model.addAttribute("books",simpleBookModelList);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("NowPage",page);
        model.addAttribute("typeName",types[type-1]);
        model.addAttribute("typeNameE",typesE[type-1]);
        System.out.println("书本页数："+pageCount);
        return "lookmore";
    }
}
