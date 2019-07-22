jQuery(function(){
    var reg4 =jQuery(".modify-btn");@localhost

    //实现花瓣飘落特效
    jQuery(document).snowfall('clear');
    jQuery(document).snowfall({
         image: "asserts/img/flower.jpg",
         flakeCount:30,
         minSize: 10,
         maxSize: 20,
     });
    //实现tab选项卡的切换效果
    /*jQuery("#nav #tab li").click(function(){
        //通过.index()方法获取元素下标,从0开始，赋值给某个变量
        var _index = jQuery(this).index();
        //让内容框的第_index个显示出来，其他的被隐藏
        jQuery("#content>div").eq(_index).show().siblings().hide();
        //jQuery("#content #Focus_Fans").hide();
        //改变选中时候的选项框的样式，移除其他几个选项的样式
        jQuery(this).addClass("select").siblings().removeClass("select");
    })*/
    //第一个点击事件
    jQuery("#nav #tab li:first-child").click(function(){
        jQuery("#content>div:first-child").show();
        jQuery("#content>div:nth-child(2)").hide();
        jQuery("#content>div:nth-child(3)").hide();
        jQuery(this).addClass("select").siblings().removeClass("select");
        jQuery(this).attr('select','selected');
    })

    //第二个点击事件
    jQuery("#nav #tab li:nth-child(2)").click(function(){
        jQuery("#content>div:nth-child(2)").show();
        jQuery("#content>div:first-child").hide();
        jQuery("#content>div:nth-child(3)").hide();
        jQuery(this).addClass("select").siblings().removeClass("select");
        jQuery(this).attr('select','selected');
    })
    //第三个点击事件
    jQuery("#click_focus").click(function(){
        jQuery("#content>div:nth-child(3)").show();
        jQuery("#content #Focus_Fans #focus1").show();
        jQuery("#content>div:first-child").hide();
        jQuery("#content>div:nth-child(2)").hide();
        jQuery("#content #Focus_Fans #fans1").hide();
        jQuery(this).addClass("select").siblings().removeClass("select");
        jQuery(this).attr('select','selected');
    })
//第四个点击事件
    jQuery("#click_fans").click(function() {
        jQuery("#content>div:nth-child(3)").show();
        jQuery("#content #Focus_Fans #fans1").show();
        jQuery("#content #Focus_Fans #focus1").hide();
        jQuery("#content>div:first-child").hide();
        jQuery("#content>div:nth-child(2)").hide();
        jQuery(this).addClass("select").siblings().removeClass("select");
        jQuery(this).attr('select','selected');
    })





    //实现用户所拥有书籍的翻页功能
    var book=document.getElementById("book");
    var pages=book.getElementsByClassName("page");
    var count=0;
    var pageNumber=pages.length-1,rota = (-180);
    var cover=jQuery('#cover');

    jQuery('body').on('click','#book',function(){
        // jQuery("#book").css("left","50%");
        // jQuery("#cover").css("left","50%");
        // cover.css('left','460px');
        pages[pageNumber].style.transform="rotateY("+rota+"deg)";
        pageNumber--;
        count++;
        if(pageNumber<0){
            count=0;
            cover.css("display",'block');
            for(var i=0;i<pages.length;i++){
                pages[i].style.transform="rotateY(0deg)";
            }
            document.getElementById('cover').style.transform="rotateY(0deg)";
            book.style.left="238px";
            document.getElementById('cover').style.left="458px";
            pageNumber=pages.length-1;
        }
        if(count>0){
            cover.css("display",'none');
        }
    })
    jQuery('body').on('click','#cover',function(){
        // jQuery("#book").css("left","50%");
        // cover.css('left','460px');
        cover.css('transform','rotateY(180deg)');
    })
    

    /*修改头像弹出框 */
    var myAlert1 = jQuery("#alert");
    var myMask1=jQuery('#mask1');
    var reg1 = jQuery("#modify");
    var mclose1 = jQuery("#mclose");
    var msave = jQuery("#mcancel");
    reg1.click(function(){
        myMask1.css("display","block");
        myAlert1.css("display","block");
        myAlert1.css("position","absolute");
        myAlert1.css("top","40%");
        myAlert1.css("left","40%");
        myAlert1.css("marginTop","-75px");
        myAlert1.css("marginLeft","-150px");
        jQuery(document).snowfall('clear');
    });
    mclose1.click(function(){
        myAlert1.css("display","none");
        myMask1.css("display","none");
        jQuery(document).snowfall({
            image: "asserts/img/flower.jpg",
            flakeCount:30,
            minSize: 10,
            maxSize: 20,
        });
    });
   var pic_url;
    //预览效果
    jQuery("#img-file").change(function(){
        var url = URL.createObjectURL(jQuery(this)[0].files[0]);
        pic_url = url;
        //判断是否选择了图片
        if(pic_url==null){
            alert("请选择图片");
            return;
        }
        jQuery("#yulan").css("display","block");
        jQuery("#yulan").attr("src",url);
        jQuery(".vice-drop-area").css("display","none");
        return url;
    });
    //将头像上传并保存,(保存还未实现)
    jQuery("#msave").click(function(){
        /*将用户修改的头像放到后台进行保存 */
        var id =jQuery("#user_id").text();
        var photo={
            "id":id,
            "photoUrl":pic_url
        }
        jQuery.ajax({
            url:"/eidtphoto",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify(photo),
            success:function (data) {
                if(data.state=="success"){
                    jQuery('#user-pic').attr("src",URL.createObjectURL(jQuery('#img-file')[0].files[0]));
                    mclose1.click();
                    jQuery("#yulan").css("display","none");
                    jQuery(".vice-drop-area").css("display","block");
                }else {
                    alert(data.state);
                }
            },
            fail:function (data) {
                alert("未知错误");
            }
        })

    })
    
       /*修改资料弹出框 */
       var myAlert2 = jQuery("#malert");
       var myMask2=jQuery('#mask2');
       var reg2 = jQuery("#modifymsg");
       var mclose2 = jQuery("#pic2");
	   reg2.click(function(){
		   myMask2.css("display","block");
		   myAlert2.css("display","block");
		   myAlert2.css("position","absolute");
		   myAlert2.css("top","40%");
		   myAlert2.css("left","50%");
		   myAlert2.css("marginTop","-75px");
		   myAlert2.css("marginLeft","-150px");
           jQuery(document).snowfall('clear');
	   })
	   mclose2.click(function(){
		   myMask2.css("display","none");
		   myAlert2.css("display","none");
           jQuery (document).snowfall({
		        image: "asserts/img/flower.jpg",
		        flakeCount:30,
		        minSize: 10,
		        maxSize: 20,
		    });  
       });

       //对感悟规定字数进行倒数
    jQuery('#think1').bind('input propertychange',function(){
            var number = jQuery("#think1").val().length;
        jQuery("#wordnumber").html(27-number);
        });
        
        //对两次输入密码进行判断，看两次输入是否一致
    jQuery("#repwd1").bind('input propertychange',function(){
            //判断两次输入密码是否一致
            if( jQuery("#pwd1").val() != jQuery(this).val())
            {
                jQuery('.warn').html('两次输入密码不一致！');
            }
            else
                jQuery('.warn').html('');
        });

    jQuery("#pwd1").bind('input propertychange',function(){
            //判断两次输入密码是否一致
            if( jQuery("#repwd1").val() != jQuery(this).val())
            {
                jQuery('.warn').html('两次输入密码不一致！');
            }
            else
                jQuery('.warn').html('');
        });

        
        
       //点击确认修改事件
    jQuery("#btn2").click(function(){
           //获取修改的信息,即弹窗中的信息
            var name = jQuery("#name1").val();
            var pwd = jQuery("#pwd1").val();
            var repwd = jQuery("#repwd1").val();
            var think = jQuery("#think1").val();
            var id =jQuery("#user_id").text();
            var number = jQuery("#user_number").text();
            var photo = jQuery("#user_photo").text();
            var own_count = jQuery("#ownbook-num").text();
            var borro_count =jQuery("#sharebook-num").text();
           var person = {
               "id":id,
               "nickname":name,
               "password":pwd,
               "inspiration":think,
               "number":number,
               "photoUrl":photo,
               "own_count":own_count,
               "borrow_count":borro_count
           }
        if( jQuery("#pwd1").val() == jQuery('#repwd1').val()) {
            jQuery.ajax({
                url: "/userupdata",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.state == "success") {
                        //修改成功
                        //将原本的信息修改为修改后的信息
                        jQuery("#user-name").html(name);
                        jQuery("#pwd").html(pwd);
                        jQuery("#user-content").html(think);
                        $('.notice-msg').html('修改成功！');
                        $(".notice-box").slideToggle(800);
                        $(".notice-box").slideToggle(800);
                    } else {
                        //信息修改失败
                        $('.notice-msg').html('修改失败！');
                        $(".notice-box").slideToggle(1300);
                        $(".notice-box").slideToggle(1300);
                    }
                },
                fail: function (data) {
                    //处理未知错误
                    $('.notice-msg').html('未知错误！');
                    $(".notice-box").slideToggle(1300);
                    $(".notice-box").slideToggle(1300);
                }
            });
            mclose2.click();
        }
        else {
            $('.notice-msg').html('修改失败！');
            $(".notice-box").slideToggle(1300);
            $(".notice-box").slideToggle(1300);
        }

    });

        /*上传书籍弹出框*/
        var myAlert3 = jQuery("#upld");
        var myMask3=jQuery('#mask3');
        var reg3 = jQuery("#uploadbook");
        var mclose3 =jQuery("#upclose");
        reg3.click(function()
        {
            myMask3.css("display","block");
            myAlert3.css("display","block");
            myAlert3.css("position","absolute");
            myAlert3.css("top","30%");
            myAlert3.css("left","45%");
            myAlert3.css("marginTop","-75px");
            myAlert3.css("marginLeft","-150px");
            jQuery(document).snowfall('clear');
        });
        mclose3.click(function()
        {
            myMask3.css("display","none");
            myAlert3.css("display","none");
            jQuery(document).snowfall({
                    image: "asserts/img/flower.jpg",
                    flakeCount:30,
                    minSize: 10,
                    maxSize: 20,
            });  
        });
        //选择书籍封面
    var newBookUrl;
    jQuery("#bookimg-file").change(function(){
           newBookUrl = URL.createObjectURL(jQuery(this)[0].files[0]);
        jQuery("#img1").attr("src",newBookUrl);
        });

    //获取上传框里面的内容,并添加书本
    jQuery("#newBook").click(function () {
        //获取数据并进行校验
        var userId = jQuery("#user_id").text();
        var book_name = jQuery("#upname").val();
        var author = jQuery("#upauthor").val();
        var text  = jQuery("#suibi").val();
        var cover = newBookUrl;

        var wangpan = jQuery("#wangpanurl").val();
        var xianxia = jQuery("#wayconnect").val();
 var typeCount =0;
        var type1 = jQuery("#check1").is(':checked')==true?1:0;
        var type2 = jQuery("#check2").is(':checked')==true?1:0;
        var type3 = jQuery("#check3").is(':checked')==true?1:0;
        var type4 = jQuery("#check4").is(':checked')==true?1:0;
        var type5 = jQuery("#check5").is(':checked')==true?1:0;
        //数据校验
        if(type1==1)typeCount++;
        if(type2==1)typeCount++;
        if(type3==1)typeCount++;
        if(type4==1)typeCount++;
        if(type5==1)typeCount++;
        if(cover==null){
            $('.notice-msg').html('请选择书籍封面！');
            $(".notice-box").slideToggle(1300);
            $(".notice-box").slideToggle(1300);
            return;
        }
       if(typeCount>3){
           $('.notice-msg').html('最多选择三类标签！');
           $(".notice-box").slideToggle(1300);
           $(".notice-box").slideToggle(1300);
           return;
       }
       if(typeCount==0)
       {
           $('.notice-msg').html('请选择标签分类！');
           $(".notice-box").slideToggle(1300);
           $(".notice-box").slideToggle(1300);
           return;
       }

        if(jQuery("#upname").val()==""||jQuery("#upname").val()==" "){
            //改变样式提醒不能为空
            $('.upname>span').css('display','inline');
            return;
        }
        else{
            $('.upname>span').css('display','none');
        }
        if(jQuery("#upauthor").val()==""||jQuery("#upauthor").val()==" "){
            $('.upauthor>span').css('display','inline');
            return;
        }
        else
        {
            $('.upauthor>span').css('display','none');
        }
        if(jQuery("#wangpanurl").val()==""&&jQuery("#wayconnect").val()==""){
            $('.way>span').css('display','inline');
            return ;
        }
        else
        {
            $('.way>span').css('display','none');
        }
        if(jQuery("#suibi").val()==""||jQuery("#suibi").val()==" "){
            $('.suibi>span').css('display','inline');
            return;
        }
        else
        {
            $('.suibi>span').css('display','none');
        }

        if(wangpan=="")wangpan="无";
        if(xianxia=="")xianxia="无";

        //数据封装
        var type ={
            "type1":type1,
            "type2":type2,
            "type3":type3,
            "type4":type4,
            "type5":type5,
        }
        var book_source={
           "wangpan":wangpan,
            "xianxia":xianxia,

        }
        var newBook={
            "user_id":userId,
            "author":author,
            "book_name":book_name,
            "coverUrl":cover,
            "text":text,
            "zanCount":0,
            "status":1, //默认状态是可以借出的
            "type":type,
            "bookSource":book_source

        }
        //采用ajax 传递数据
        //使用load
        jQuery.ajax({
            url:"/newBook",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(newBook),
            success:function (resp) {

                if(resp.state=="success"){
                    //动态创建书本
                    // alert("书本添加成功");

                    //局部刷新
                    jQuery("#book").load("/newBookflash",{
                        "id":jQuery("#user_id").text()
                    });
                    mclose3.click();
                }else {
                    // alert("同名书本已存在你可以考虑修改"+book_name+"的相关信息");
                    mclose3.click();
                }
            },
            fail:function (resp) {
                // alert(resp.message+"未知错误");
            }
        });

    });
        

        
        /*修改书籍弹出框 */
        var myAlert4 =jQuery("#upld1");
        var myMask4=jQuery('#mask4');
        var reg4 = jQuery(".modify-btn");//定义为全局变量，在每一次添加书籍后再次获取赋值
        var mclose4 = jQuery("#upclose2");
        var deletereg = jQuery('.delete-btn');

        //点击“修改书籍”按钮弹出修改框
        // reg4.each(function(){
        //     jQuery(this).on('click',function(event){
        //         alert("1");
        //         // myMask4.css('display','block');
        //     // myAlert4.css('position','absolute');
        //         // myA    lert4.css('top','30%');
        //         // myAlert4.css('display','block');
        //    // myAlert4.css('left','50%');
        //         // myAlert4.css('marginTop','-75px');
        //         // myAlert4.css('marginLeft','-150px');
        //         // jQuery(document).snowfall('clear');
        //         // if(window.event){
        //         //     event.cancelBubble  = true;//IE下阻止冒泡
        //         // }else{
        //         //     event.stopPropagation();
        //         // }
        //
        //     })
        // });



        mclose4.click(function(){
            myAlert4.css('display','none');
            myMask4.css('display','none');
            jQuery(document).snowfall({
                image: "asserts/img/flower.jpg",
                flakeCount:30,
                minSize: 10,
                maxSize: 20,
            });

            //要清除多选项中的数据checked
            jQuery("#check6").removeAttr("checked");
            jQuery("#check7").removeAttr("checked");
            jQuery("#check8").removeAttr("checked");
            jQuery("#check9").removeAttr("checked");
            jQuery("#check10").removeAttr("checked");
        });

//修改书本的信息
    jQuery("#updataBook").click(function () {
        // alert("上传数据");
        //获取cover这个元素
        var coverDiv = jQuery("#cover");
        //获取数据
        var id = jQuery("#BookId").text();
        var status = jQuery("#"+id+"status").text();
        var zan_count = jQuery("#"+id+"zan").text();
        var typeId = jQuery("#"+id+"typeId").text();
        var sourceId = jQuery("#"+id+"sourceId").text();
        // alert(sourceId+"sourceId");

        var book_name = jQuery("#upname2").val();
        var book_author = jQuery("#upauthor2").val();
        var cover =  jQuery("#img2").attr('src');
        var wangpan = jQuery("#wangpanurl2").val();
        var xianxia = jQuery("#wayconnect2").val();
        var typecount2=0;
        var type1 =  jQuery("#check6").is(':checked');
        var type2 =  jQuery("#check7").is(':checked');
        var type3 =  jQuery("#check8").is(':checked');
        var type4 =  jQuery("#check9").is(':checked');
        var type5 =  jQuery("#check10").is(':checked');
        if(type1==true){
            type1=1;
            typecount2++;
        }else {type1=0;}
        if(type2==true){
            type2=1;
            typecount2++;
        }else {type2=0;}
        if(type3==true){
            type3=1;
            typecount2++;
        }else {type3=0;}
        if(type4==true){
            type4=1;
            typecount2++;
        }else {type4=0;}
        if(type5==true){
            type5=1;
            typecount2++;
        }else {type5=0;}
        // alert("修改类别："+type1+type2+type3+type4+type5);
        //数据校验
        if(typecount2>3){
            $('.notice-msg').html('最多选择三类标签！');
            $(".notice-box").slideToggle(1300);
            $(".notice-box").slideToggle(1300);
            return;
        }
        if(typecount2==0)
        {
            $('.notice-msg').html('请选择标签分类！');
            $(".notice-box").slideToggle(1300);
            $(".notice-box").slideToggle(1300);
            return;
        }

        if(jQuery("#upname2").val()==""||jQuery("#upname2").val()==" "){
            //改变样式提醒不能为空
            $('.upname2>span').css('display','inline');
            return;
        }
        else{
            $('.upname2>span').css('display','none');
        }
        if(jQuery("#upauthor2").val()==""||jQuery("#upauthor2").val()==" "){
            $('.upauthor2>span').css('display','inline');
            return;
        }
        else
        {
            $('.upauthor2>span').css('display','none');
        }
        if(jQuery("#wangpanurl2").val()==""&&jQuery("#wayconnect2").val()==""){
            $('.way2>span').css('display','inline');
            return ;
        }
        else
        {
            $('.way2>span').css('display','none');
        }
        if(jQuery("#suibi2").val()==""||jQuery("#suibi2").val()==" "){
            $('.suibi2>span').css('display','inline');
            return;
        }
        else
        {
            $('.suibi2>span').css('display','none');
        }

        if(jQuery("#wangpanurl2").val()=="")wangpan="无";
        if(jQuery("#wayconnect2").val()=="")xianxia="无";


        var text = jQuery("#suibi2").val();
         var type ={
             "id":typeId,
             "type1":type1,
             "type2":type2,
             "type3":type3,
             "type4":type4,
             "type5":type5
         }
         var booksource ={
                    "id":sourceId,
             "wangpan":wangpan,
             "xianxia":xianxia
         }
         var book ={
             "id":id,
             "book_name":book_name,
             "author":book_author,
             "coverUrl":cover,
             "user_id":jQuery("#user_id").text(),
             "text":text,
             "status":status,
             "zanCount":zan_count
         }
         //使用Ajax传递，load 进行局部刷新


        jQuery("#book").load("/modifybook",{
            "id":id,
            "book_name":book_name,
            "author":book_author,
            "coverUrl":cover,
            "user_id":jQuery("#user_id").text(),
            "text":text,
            "status":status,
            "zanCount":zan_count,
            "sourceid":sourceId,
            "wangpan":wangpan,
            "xianxia":xianxia,
            "typeid":typeId,
            "type1":type1,
            "type2":type2,
            "type3":type3,
            "type4":type4,
            "type5":type5

        })
        mclose4.click();//关闭弹窗
        //将cover这个div 添加到id="Page"  之后
    })

    /*修改书籍状态弹出框 start*/
    var myAlert5 = jQuery('#alert5');
    var myMask5=jQuery('#mask5');
    var reg5 = jQuery('.con-btn');
    var myclose5 = jQuery("#close5");
    // jQuery('body').on('click','.con-btn',function(){
    //     myMask5.css("display",'block');
    //     myAlert5.css('display','block');
    //     myAlert5.css('position','absolute');
    //     myAlert5.css('top','20%');
    //     myAlert5.css('left','50%');
    //     myAlert5.css('marginTop','-75px');
    //     myAlert5.css('marginLeft','-150px');
    //     jQuery('body').css('overflow','hidden');
    //     jQuery(document).snowfall('clear');
    //
    // });
    //确定按钮
    jQuery(".status_btn").click(function(){
        var a = jQuery('input:radio[name="radiocon"]:checked').val();
        // alert(a);
        myAlert5.css('display','none');
        myMask5.css('display','none');
        //判断书本的借阅状态
        var status =1;
        if(a=="不可借阅"){
            status = 0;
        }
        //获取书本的id
        var bookId = jQuery("#Status_book_id").text();
        // alert(bookId+"  "+status);
        //使用load 完成局部刷新
        jQuery("#book").load("/bookStatus",{
            "bookId":bookId,
            "status":status
        })

    });
    myclose5.click(function(){
        myAlert5.css('display','none');
        myMask5.css('display','none');
        jQuery(document).snowfall({
            image: "asserts/img/flower.jpg",
            flakeCount:30,
            minSize: 10,
            maxSize: 20,
        });
    })
    /*修改书籍状态弹出框 end*/


    //将要修改的页面的内容填充到弹出修改资料页面(皮皮写)
    jQuery('.book-bottom').on('click','.modify-btn',function(){
            //函数是在click动态创建时绑定，函数开头不要修改

            //var top1 = jQuery(this).parents('.page').children('div').get(0);//获取到.book-top
            //var bottom1 = jQuery(this).parent();//获取到.book-bottom;

        })

        //为动态创建的书籍页面添加“删除书籍”事件按钮
    jQuery('.book-bottom').on('click','#delete-btn',function(){

        })


})