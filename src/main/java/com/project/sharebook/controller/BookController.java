package com.project.sharebook.controller;


import com.project.sharebook.entities.Book;
import com.project.sharebook.entities.BookSource;
import com.project.sharebook.entities.Type;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.BookModel;
import com.project.sharebook.modelobject.DetailedBookMsg;
import com.project.sharebook.modelobject.SearchBookModel;
import com.project.sharebook.modelobject.SimpleBookModel;
import com.project.sharebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//完成书籍的增删改查
@Controller
public class BookController {
    @Autowired
    BookService bookService;

    //删除书本:在book 的表中删除这项纪录,删除在type 表中，source 表中的记录
    @PostMapping("/debook")
    public String DeBook(@RequestParam("bookId") Integer book_id,@RequestParam("userId") Integer user_id,@RequestParam("typeId")Integer type_id,@RequestParam("sourceId")Integer source_id, Model model) throws BusinessException {
        System.out.println("删除书本");
        bookService.DelectBook(user_id,book_id,type_id,source_id);
        List<BookModel> bookModelList = bookService.GetOwnBooks(user_id);
        model.addAttribute("books",bookModelList);//用户拥有的书本
        return "personal-center::Books";
    }
    //首页点击进去的书籍的具体信息页面，重新定义Model 包括书籍的基本信息，(要设置点赞数)，评论，同名书籍大的基本信息
    //根据书本的id 获取书籍的具体信息：包括书本的拥有着和对书本的最新评论
    @GetMapping("/getMsgs")//获取详细信息
    public String GetDetaileMsg(@RequestParam("id")Integer id ,Model model) throws BusinessException {
        System.out.println("获取书本详细信息的id:"+id);
        DetailedBookMsg bookMsg = bookService.GetBookMsg(id);
        model.addAttribute("deMsg",bookMsg);
        System.out.println("获取书籍详细信息");
        return "book";//展示书籍信息的详细页面
    }
    //修改书本, @RequestParam("type")Type type, @RequestParam("booksource")BookSource bookSource
   @PostMapping("/modifybook")
    public String ModifyBook(@RequestParam("id")Integer id,@RequestParam("book_name")String name ,@RequestParam("author")String author,
                           @RequestParam("coverUrl")String cover,@RequestParam("user_id")Integer user_id,@RequestParam("text")String text,
                           @RequestParam("status")Integer status,@RequestParam("zanCount")Integer zanCount,@RequestParam("sourceid")Integer sid,
                           @RequestParam("wangpan")String wangpan,@RequestParam("xianxia")String xianxia,@RequestParam("typeid")Integer tid,
                           @RequestParam("type1")Integer type1,@RequestParam("type2")Integer type2,@RequestParam("type3")Integer type3,
                           @RequestParam("type4")Integer type4,@RequestParam("type5")Integer type5,Model model) throws BusinessException {
       Book book = new Book();
       book.setId(id);
       book.setBook_name(name);
       book.setAuthor(author);
       book.setZanCount(zanCount);
       book.setStatus(status);
       book.setCoverUrl(cover);
       book.setText(text);
       book.setUser_id(user_id);

       //获取途径的设置
       BookSource source = new BookSource();
       source.setId(sid);
       source.setBook_id(id);
       source.setWangpan(wangpan);
       source.setXianxia(xianxia);

        Type type = new Type();
        type.setId(tid);
        type.setType1(type1);
        type.setType2(type2);
        type.setType3(type3);
        type.setType4(type4);
        type.setType5(type5);
        type.setBook_id(id);
        System.out.println("修改类别："+type);
        //进行数据的修改
       bookService.ModifyBook(book,source,type);
       //拥有的书本
       List<BookModel> bookModelList = bookService.GetOwnBooks(user_id);
       model.addAttribute("books",bookModelList);//用户拥有的书本
        return "personal-center::Books";
   }
   //完成书本添加之后的局部刷新,书本的添加使用ajax 完成
   @PostMapping("/newBookflash")
    public String newBookFlash(@RequestParam("id")Integer id, Model model) throws BusinessException {
       List<BookModel> bookModelList = bookService.GetOwnBooks(id);
       model.addAttribute("books",bookModelList);//用户拥有的书本
        return "personal-center::Books";
   }
   //完成书本状态的修改
    @PostMapping("/bookStatus")
    public String ChangeStatus(@RequestParam("bookId")Integer book_id,@RequestParam("status")Integer status,Model model) throws BusinessException {

        Book book = bookService.ChangeStatus(book_id,status);
        List<BookModel> bookModelList = bookService.GetOwnBooks(book.getUser_id());
        model.addAttribute("books",bookModelList);//用户拥有的书本
        return "personal-center::Books";
    }

    //完成书籍的搜索,首页的
    @GetMapping("/searchBook")
    public String searchBook(@RequestParam("data")String txt,Model model){
        List<SearchBookModel> searchBookModelList = bookService.SearchBook(txt);
        model.addAttribute("books",searchBookModelList);
        return "search";
    }
    //lookmore 页面 顶部下拉框的请求
    @PostMapping("/TypeName")
    public String getTypeName(@RequestParam("type")Integer type,@RequestParam("page")Integer page,Model model){
        System.out.println("type:"+type+"page"+page);

        String[] types ={"小说","科技教育","文艺","历史文化","经济/教育"};
        String[] typesE ={"NOVEL","SCIENCE&TECHNOLOGY EDUCATION","LITERATURE","HISTORY AND CULTURE","HCONOMIC MANAGEMENT"};
        //返回的类型
        model.addAttribute("typeName",types[type-1]);
        model.addAttribute("typeNameE",typesE[type-1]);


        return "lookmore::TypeName";
    }
    @PostMapping("/MoreBooks")
    public String getMoreBooks(@RequestParam("type")Integer type,@RequestParam("page")Integer page,Model model)
    {
        //获取返回的书本信息
        List<SimpleBookModel> simpleBookModelList = bookService.getMoreBookOnePage(type,page);

        model.addAttribute("books",simpleBookModelList);


        return "lookmore::MoreBooks";
    }
    @PostMapping("/pageNumber")
    public String pageNumber(@RequestParam("type")Integer type,@RequestParam("page")Integer page,Model model){

        //获取一共的页数
        Integer pageCount = bookService.getPageCount(type);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("NowPage",page);

        System.out.println("书本页数："+pageCount);
        System.out.println("当前页："+page);
        return "lookmore::PageNumber";
    }
}
