jQuery(function () {

    // 动态监听用事件委托
    jQuery("body").delegate(".comment","propertychange input",function () {
        //判断是否输入了内容
        if(jQuery(this).val().length > 0)
        {
            //让按钮可用
            jQuery(".send").prop("disabled",false);
        }
        else {
            //让按钮不可用
            jQuery(".send").prop("disabled",true);
        }
    });
    //监听顶点击
    jQuery("body").delegate(".infoTop","click",function () {
        jQuery(this).text(parseInt(jQuery(this).text())+1);
    })

    //创建节点方法
    function createEle(text) {
        var comment=jQuery(
            "<div class=\"part\">\n" +
            "            <img src=\"image/头像.jpg\" alt=\"\">\n" +
            "            <div class=\"usercomment\">\n" +
            "            <table>\n" +
            "            <tr>\n" +
            "            <!--darksalmon-->\n" +
            "            <td style=\"font-family:'黑体';font-size: 16px; font-weight: bold;\"><span style=\"color: darksalmon;font-size: 18px;\">用户名:</span></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "        <td style=\"display: block;text-indent: 2em\">"+text+"</td>\n" +
            "        </tr>\n" +
            "        </table>\n" +
            "        <!--点赞数和发表日期-->\n" +
            "        <div class=\"like_count\">\n" +
            "            <time>"+formatDate()+"</time>\n" +
            "        </div>\n" +
            "        </div>\n" +
            "        </div>")

        return comment;
    }

    //生成事件方法
    function formatDate() {
        var date=new Date();
        var arr=[date.getFullYear()+"-"+(date.getMonth()+1)
        +"-"+date.getDay()+" "+date.getHours()+":"+date.getMinutes()+
        ":"+date.getSeconds()];
        //变字符串
        return (arr.join());
    }
    formatDate();
    //    点击蒙版
    jQuery(".publish_text").click(function (event) {
        var user = jQuery("#btn_login").text();

        if(user=="登录")
        {
            jQuery('.notice-msg').html("请先登录");
            jQuery(".notice-box").slideToggle(1500);
            jQuery(".notice-box").slideToggle(1500);
        }

        else {

            if(user!="登录"){
                //阻止冒泡

                stopBubble(event);
                jQuery("#write_panel").css('display','block');
                jQuery("body").css('overflow','hidden');
                jQuery("#write").css('display','block');
            }
        }

    })
    jQuery(".close_img").click(function () {
        jQuery("#write_panel").css('display','none');
        jQuery("#write").css('display','none');
        jQuery("body").css('overflow','auto');
    })

    jQuery(".comment").bind('input onpropertychange',function () {
        var text=jQuery(".comment").val().length;
        jQuery("#rest_num").html(300-text);
    })
    //点赞
    var flag =0;
    var ZanCount = jQuery("#Count").text();
    jQuery("#ZanCount").click(function () {
        //点赞数的增加
        if(flag==0){
            ZanCount++;
            jQuery('#ZanCount img').attr('src','/asserts/img/点赞后.png');
            flag =1;
        }else {
           ZanCount--;
            jQuery('#ZanCount img').attr('src','/asserts/img/点赞前.png');
           flag=0;
        }
        jQuery("#Count").text(ZanCount);
        //实时更新到数据库
        jQuery.ajax({
           url:"/zan",
            type:"post",
            data:{
               "id":jQuery("#bookId").text(),
                "flag":flag
            },
            success:function (data) {
                if(data.state=="fail"){
                    alert("点赞失败，请检查网络");
                }
            }
        })
    })
//发表评论 send
    //能弹出评论的框证明该用户已经登陆
    //监听发布按钮的点击  send 按钮
    jQuery(".send").click(function () {
        var bookId =jQuery("#bookId").text() ;
        //判断是否已经登录没有登录要先登录

        //拿到用户输入的内容
        var text=jQuery(".comment").val();

        if(text.length!=0) {
            //根据内容创建节点
            var comment = jQuery(text);
            //插入微博
            jQuery(".messageList").prepend(comment);
            jQuery(".comment").val("");
            jQuery("#write_panel").css('display', 'none');
            jQuery("#write").css('display', 'none');
            jQuery("body").css('overflow', 'auto');


            //    向后端传送数据,要获取当前的时间

            var userId = jQuery("#UserId").text();

            var book_name = jQuery("#bookName").text();

            //获取当前时间
            var myDate = new Date();
            var month = myDate.getMonth()+1;


            var date = myDate.getFullYear()+"年"+month+"月"+myDate.getDate()+"日";

            var mycomment = {
                "user_id":userId,
                "book_id":bookId,
                "book_name":book_name,
                "comment":text,
                "date":date
            }

            //  ajax 传递
            jQuery.ajax({
                url:"/comment",
                type:"post",
                contentType:"application/json",
                data:JSON.stringify(mycomment),
                success:function (data) {
                    if(data.state=="success"){
                        //评论成功

                        //跳转到 显示书籍详细信息的页面

                        window.location.href="/getMsgs?id="+bookId;

                        //实现局部刷新

                    }
                },
                fail:function (data) {
                    //处理未知错误；

                }
            });
        }
    })

})