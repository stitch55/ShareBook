jQuery(function () {
    jQuery("#login_in").click(function (event) {
        //阻止冒泡
        stopBubble(event);
        jQuery("#panel").css('display','block');
        jQuery("#login").css('display','block');

        jQuery("body").css('overflow','hidden');
    })

    jQuery("#rigster").click(function (event) {
        stopBubble(event);
        jQuery("#panel").css('display','block');
        jQuery("#reg").css('display','block');
        jQuery("body").css('overflow','hidden');
    })
    jQuery(document).click(function (event) {
        var e=event || window.event;
        //获取点击的标签
        //兼容
        var targetId=e.target ? e.target.id:e.srcElement.id;
        //判断
        if(targetId!='login')
        {
            //隐藏面板和蒙版
            jQuery("#panel").css('display','none');
            jQuery("#login").css('display','none');
            jQuery("#reg").css('display','none');
            //显示滚动条
            jQuery("body").css('overflow','auto');
        }
        else {
            window.location.href="http://www.baidu.com";
        }
    })
    var scroll_top=0,begin=0,end=0,timer=null;
    //监听窗口的滚动
    window.onscroll=function () {
        //获取滚动的高度
        scroll_top = scroll().top;
        //判断显示和隐藏
        scroll_top > 0 ? show(jQuery('top')) : hide(jQuery('top'));
        begin = scroll_top;
        //监听点击
        jQuery("#top").click(function () {
            //清除定时器
            clearInterval(timer);
            //开启缓动动画
            timer = setInterval(function () {
                begin = begin + (end - begin) / 20;

                //scrollTo(x,y):把内容滚动到指定的坐标
                window.scrollTo(0, begin);

                //清除定时器
                if (Math.round(begin === end)) {
                    clearInterval(timer);
                }
            }, 20)
        })
    }

//处理搜索事件
    jQuery("#searchBook").click(function () {
        //获取input 框中的内容
        var data = jQuery("#query").val();
        // alert(data);
        //页面的跳转
        window.location.href ="/searchBook?data="+data;
    })

//
    jQuery("#community").click(function () {
        jQuery(this).addClass("active");
        jQuery("#first").removeClass("active");
    })
})
function show(obj) {
    return obj.css('display','block');
}
function hide(obj) {
    return obj.css('display','none');
}