package com.project.sharebook.service;


import com.project.sharebook.entities.Book;
import com.project.sharebook.entities.BookSource;
import com.project.sharebook.entities.Type;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.modelobject.BookModel;
import com.project.sharebook.modelobject.DetailedBookMsg;
import com.project.sharebook.modelobject.SearchBookModel;
import com.project.sharebook.modelobject.SimpleBookModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BookService {
    //首页返回书籍信息（查找，并按照点赞排序）
    List<SimpleBookModel> getBooksEachType(Integer type) throws BusinessException;

    //获取更多的书本,获取一页
 List<SimpleBookModel> getMoreBookOnePage(Integer type,Integer page);
 //获取一共的页数
   Integer getPageCount(Integer type);
   //获取某一类的
    Integer getBooksCountByType(Integer type);
   //根据书本的id 查找书本相关的信息展示在首页点击去的书籍详细页面上
    DetailedBookMsg GetBookMsg(Integer id) throws BusinessException;
    //根据用户的id 获取它拥有的所有的书籍的基本信息
    List<BookModel> GetOwnBooks(Integer id) throws BusinessException;
    //书籍赞数的改变
    String ZanCount(Integer id,Integer flag);
    //删除书籍
    void DelectBook(Integer user_id, Integer id, Integer type_id, Integer source_id);

    //修改书本的相关信息
    void ModifyBook(Book book, BookSource source, Type type);

    //添加新的书本
    BookModel AddNewBook(BookModel bookModel);

    //修改书本的而借阅状态
    Book ChangeStatus(Integer book_id,Integer status);

    //完成书本搜索
    List<SearchBookModel> SearchBook(String txt);
}
