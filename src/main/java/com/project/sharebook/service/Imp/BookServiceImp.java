package com.project.sharebook.service.Imp;

import com.project.sharebook.entities.*;
import com.project.sharebook.error.BusinessException;
import com.project.sharebook.error.EmBusinessError;
import com.project.sharebook.modelobject.*;
import com.project.sharebook.repository.*;
import com.project.sharebook.service.BookService;
import com.project.sharebook.utils.Constant;
import org.hibernate.query.criteria.internal.expression.SizeOfPluralAttributeExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    BookSourceRepository bookSourceRepository;

    //首页根据点赞数排序返回
    //返回首页需要的所有书本，一共五类，每类8本
    @Override
    public List<SimpleBookModel> getBooksEachType(Integer type) throws BusinessException {
       //在type 表中查询 type=1 的书本
        List<Type> typeList = new ArrayList<>();
        if(type==1){
            typeList=typeRepository.getType1();
        }else if(type==2){
            typeList=typeRepository.getType2();
        }else if (type==3){
            typeList=typeRepository.getType3();
        }else if (type==4){
            typeList=typeRepository.getType4();
        }else if (type==5){
            typeList=typeRepository.getType5();
        }
        //根据查找到的types 中的book_id 查找对应书本的信息
        Book test = bookRepository.getOne(1);
        System.out.println("test:"+test);
        //根据查询type 的结果，获取排序后的书本
        List<SimpleBookModel> simpleBookModelList = getTypeBooks(typeList);
        return simpleBookModelList;
    }

    private List<SimpleBookModel> getTypeBooks(List<Type> typeList) {
        List<Book> bookList = new ArrayList<>();
        for(Type t:typeList){
            Book  book = bookRepository.getOne(t.getBook_id());
            bookList.add(book);
        }
        //对查找出来的书本进行点赞数的排序和数量的控制
        bookList = TypeSort(bookList);
        //dao--model
        List<SimpleBookModel> simpleBookModelList = new ArrayList<>();
        for(Book b:bookList){
            SimpleBookModel model = new SimpleBookModel();
            BeanUtils.copyProperties(b,model);
            simpleBookModelList.add(model);
        }
        return simpleBookModelList;
    }

    //每一次获取多一页的书本
    @Override
    public List<SimpleBookModel> getMoreBookOnePage(Integer type, Integer page) {
       //type 的分页查询
        List<Type> typeList =new ArrayList<>();
        Integer start = (page-1)*8;

        if(type==1){
            typeList = typeRepository.getMorePageBooksByType1(start,8);
        }else if(type==2){
            typeList = typeRepository.getMorePageBooksByType2(start,8);
        }else if (type==3){
            typeList = typeRepository.getMorePageBooksByType3(start,8);
        }else if(type==4){
            typeList = typeRepository.getMorePageBooksByType4(start,8);
        }else if (type==5){
            typeList = typeRepository.getMorePageBooksByType5(start,8);
        }

        List<SimpleBookModel> simpleBookModelList = getTypeBooks(typeList);
        return simpleBookModelList;
    }
//获取书本的页数
    @Override
    public Integer getPageCount(Integer type) {
        List<Type> typeList = new ArrayList<>();
        typeList = WhichType(type);
        Integer pageCount = typeList.size()/8;
        if(typeList.size()%8!=0){
            pageCount++;
        }
        return  pageCount;
    }

   //确定某一类进行查询
    private List<Type> WhichType(Integer type) {
        List<Type> typeList = new ArrayList<>();
        if(type==1){
        typeList=typeRepository.getType1();
    }else if(type==2){
        typeList=typeRepository.getType2();
    }else if (type==3){
        typeList=typeRepository.getType3();
    }else if (type==4){
        typeList=typeRepository.getType4();
    }else if (type==5){
        typeList=typeRepository.getType5();
    }
    return typeList;
    }

    //获取某一类的书本的总数
    @Override
    public Integer getBooksCountByType(Integer type) {
        List<Type> typeList = WhichType(type);
        return typeList.size();
    }

    //根据书籍的id 查找书籍的具体信息，拥有着和最新的评论
    @Override
    public DetailedBookMsg GetBookMsg(Integer id) throws BusinessException {
        //根据书本的id 获取书本的信息
        Book book = bookRepository.getOne(id);
       BookModel bookModel = convertFromBookDaoToBookModel(book);

        //根据书本的id 查询书本的类型信息
        Type type = typeRepository.getType(id);
       bookModel.setType(type);
        //获取书本的获取途径
        BookSource source = bookSourceRepository.getBookSource(id);
        bookModel.setBookSource(source);
        //根据书本中记录的id 的值，查询拥有者的信息
        User user = userRepository.getOne(book.getUser_id());//使用getOne 容易出现的问题：找不到直接就会报错，user 也不会null
      //  Optional<User> userp = userRepository.findById(book.getUser_id());
        SimpleUserModel userModel = convertFromUserDao(user);
        //根据书本的信息查找书本的最新评论，前三个（暂定）
        List<Comment> comments = commentRepository.getByBookIdOrder(book.getId());

        //根据评论的中的user_id  获取到用户的基本信息再将评论和基本信息进行统一的封装
        List<SimpleUserModel> userModelList = GetUserModel(comments);

        //将该本书的拥有者插入到list 的0 位。后面放的就是最新的三条评论及对应的用户

        DetailedBookMsg bookMsg = new DetailedBookMsg();
        bookMsg.setOwner(userModel);
        bookMsg.setUsers(userModelList);
        bookMsg.setBookModel(bookModel);
        return bookMsg;

    }

    private BookModel convertFromBookDaoToBookModel(Book book) throws BusinessException {
        if(book==null){
            throw  new BusinessException(EmBusinessError.BOOK_NOT_EXIST);
        }
        BookModel bookModel = new BookModel();
        BeanUtils.copyProperties(book,bookModel);
        return  bookModel;
    }

    private SimpleUserModel convertFromUserDao(User user) throws BusinessException {
        SimpleUserModel model = new SimpleUserModel();
        if(user==null){
            System.out.println("发生异常");
            throw new BusinessException(EmBusinessError.USER_FIND_FAIL);
        }
         BeanUtils.copyProperties(user,model);
        return  model;
    }

    //根据评论中的user_id  获取用户的基本信息
    private List<SimpleUserModel> GetUserModel(List<Comment> comments) {
        List<SimpleUserModel> list = new ArrayList<>();
        //最新插入的信息id 是最大的，排在集合的后面；对集合中的数据进行id 由大到小的排序
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getId()>o2.getId()){
                    return -1;//从大到小排序
                }else {
                    return 0;
                }
            }
        });
        if(comments.size()>Constant.RECENT_COMENT){
            comments = comments.subList(0,Constant.RECENT_COMENT);
        }

        for(Comment c :comments){
            SimpleUserModel model = new SimpleUserModel();
            User user = userRepository.getOne(c.getUser_id());
            System.out.println(c.getUser_id());
            System.out.println(user);
            BeanUtils.copyProperties(user,model);
            model.setComment(c);
            list.add(model);
        }
        return list;
    }

//根据用户的id 获取他所拥有的所有书本的基本信息进行展示，个人中心
    @Override
    public List<BookModel> GetOwnBooks(Integer id) throws BusinessException {
        List<Book> bookList = bookRepository.getAllByUserId(id);
        if(bookList.size()==0){
            return new ArrayList<BookModel>();//没有上传书本
        }else {
            List<BookModel> bookModels = new ArrayList<>();
            for (Book book:bookList){
                //获取书本的类型
                Type type = typeRepository.getType(book.getId());
                //获取书本对的获取方式
                BookSource source = bookSourceRepository.getBookSource(book.getId());
                BookModel model = new BookModel();
                BeanUtils.copyProperties(book,model);
                model.setType(type);
                model.setBookSource(source);
                bookModels.add(model);
            }
            return bookModels;
        }
    }
    //对赞进行实时的更新
    @Override
    public String ZanCount(Integer id, Integer flag) {
        Book book = new Book();
        try{
            book = bookRepository.getOne(id);
        }catch (Exception ex){
            return "fail";
        }
        int count = book.getZanCount();
        if(flag==1){
            count++;

        }else{
           count--;
        }
        book.setZanCount(count);
        bookRepository.save(book);
        return "success";
    }

    //删除书籍
    @Override
    public void DelectBook(Integer user_id,Integer book_id,Integer type_id,Integer source_id) {
        bookRepository.deleteById(book_id);
        //type
        typeRepository.deleteById(type_id);
        //source_id
        bookSourceRepository.deleteById(source_id);
        //comment
        List<Comment> commentList = commentRepository.getComment(user_id,book_id);
        if(commentList.size()!=0){
            for (Comment c:commentList){
                commentRepository.deleteById(c.getId());
            }
        }


    }
//修改书本的信息
    @Override
    public void ModifyBook(Book book, BookSource source, Type type) {
        bookRepository.save(book);
        bookSourceRepository.save(source);
        typeRepository.save(type);

    }
//添加新的书本
    @Override
    public BookModel AddNewBook(BookModel bookModel) {
        //根据拥有者和书本的名字查找是否已经拥有当前书本
        Book test_book = bookRepository.getByUseridAndBook_name(bookModel.getBook_name(),bookModel.getUser_id());
        if(test_book==null){
            Book save_book = new Book();
            BeanUtils.copyProperties(bookModel,save_book);
            save_book = bookRepository.save(save_book);
            //讲书本的Id 赋给type 和 source
            //type
            Type type = new Type();
            BeanUtils.copyProperties(bookModel.getType(),type);
            type.setBook_id(save_book.getId());
            typeRepository.save(type);
            //source
            BookSource bookSource = new BookSource();
            BeanUtils.copyProperties(bookModel.getBookSource(),bookSource);
            bookSource.setBook_id(save_book.getId());
            //讲书本的id 赋值给 bookModle 返回
            bookModel.setId(save_book.getId());
            bookSourceRepository.save(bookSource);
        }

        return bookModel;
    }
//修改书本的状态
    @Override
    public Book ChangeStatus(Integer book_id, Integer status) {

        //根据书本的id 讲述本查找出来
        Book book = bookRepository.getOne(book_id);
        book.setStatus(status);
        //修改书本的status,讲书本存回数据库
        bookRepository.save(book);

        return book;
    }
//完成书籍搜索
    @Override
    public List<SearchBookModel> SearchBook(String txt) {

        //输入的是作者，精确查询
        List<Book> bookByAuthor = bookRepository.getBookByAuthor(txt);
        //输入的是书名 ,先精确查询
        List<Book> bookByName = bookRepository.getBookByBook_name(txt);
        //模糊查询
        List<Book> bookByNameLike = bookRepository.getBookByBook_nameByLike(txt);

        if(bookByName.size()!=0&&bookByNameLike.size()!=0){
            //排除模糊中的精确
            for (int i=0;i<bookByNameLike.size();i++){
                    if(bookByNameLike.get(i).getBook_name().equals(txt)){
                        bookByNameLike.remove(i);
                    }
            }

        }
        //将集合进行拼装
        List<Book> books = new ArrayList<>();
        if(bookByAuthor.size()!=0){
            for (Book b : bookByAuthor){
                books.add(b);
            }
        }
        if(bookByName.size()!=0){
            for (Book b : bookByName){
                books.add(b);
            }
        }
        if(bookByNameLike.size()!=0){
            for (Book b : bookByNameLike){
                books.add(b);
            }
        }
        //查询每本书的拥有者
        List<SearchBookModel> searchBookModelList = new ArrayList<>();
        if(books.size()!=0){
            for(Book b :books){
                User user = userRepository.getOne(b.getUser_id());
                //领域模型的转换
                SearchBookModel searchBookModel = new SearchBookModel();
                SimpleUserModel userModel = new SimpleUserModel();
                BeanUtils.copyProperties(b,searchBookModel);
                BeanUtils.copyProperties(user,userModel);

                //SearchBookModel 的组装
                searchBookModel.setUser(userModel);

                searchBookModelList.add(searchBookModel);
            }
        }
        return searchBookModelList;
    }

    //首页根据点赞数排名返回书本的基本信息
    //分类
    private List<Book> Sort(List<Book> bookList) {
        List<Book> type1 = new ArrayList<>();
        List<Book> type2 = new ArrayList<>();

        for(Book book :bookList){
            Type type = typeRepository.getType(book.getId());
            if (type.getType1()==1){
                type1.add(book);
            }
            if(type.getType2()==1){
                type2.add(book);
            }
        }
        type1 = TypeSort(type1);
        type2 = TypeSort(type2);
        for(Book book:type2){
            type1.add(book);
        }
        return type1;
    }
//根据点赞排序
    private List<Book> TypeSort(List<Book> type) {
        Collections.sort(type, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getZanCount()>o2.getZanCount()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        //每类展示8本书
        if(type.size()> Constant.HPAGE_BOOKCOUNT){
           type= type.subList(0,8);
        }
        return type;
    }
//领域模型的转化
    private SimpleBookModel convertFromBookDao(Book book) throws BusinessException {
        SimpleBookModel model = new SimpleBookModel();
        if(book==null){
            throw  new BusinessException(EmBusinessError.BOOK_NOT_EXIST);
        }
        BeanUtils.copyProperties(book,model);
        return model;
    }
}
