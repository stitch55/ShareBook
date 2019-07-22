package com.project.sharebook.controller;

import com.project.sharebook.modelobject.BookModel;
import com.project.sharebook.response.AjaxData;
import com.project.sharebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//处理前端向后端关于书本信息的ajax 传递
//完成
@RestController
public class BookAjaxController {
    @Autowired
    BookService bookService;
    //点赞数的刷新时更新
    @PostMapping("/zan")
    public AjaxData AddzanCount(@RequestParam("id")Integer id,@RequestParam("flag") Integer flag){
        //点击一次就会有一次的数据更新  后台数据+1 或者减一
        String state = bookService.ZanCount(id,flag);
        return AjaxData.create(id,state);
    }

    //添加新的书本
    @PostMapping("/newBook")
    public AjaxData NewBook(@RequestBody BookModel bookModel){

        System.out.println(bookModel);
        //查找这本书该用户是否已经添加
        BookModel newBook = bookService.AddNewBook(bookModel);
        if(newBook.getId()==null){//该用户已经有这本书了
            return AjaxData.create(newBook,"fail");
        }else {
            return AjaxData.create(newBook);
        }
    }
}
