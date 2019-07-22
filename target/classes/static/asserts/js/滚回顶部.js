   window.onload=function (){
        //
        var scroll_top=0,begin=0,end=0,timer=null;
        //监听窗口的滚动
        window.onscroll=function () {
            //获取滚动的高度
            scroll_top=scroll().top;
            console.log(scroll_top);
            //判断显示和隐藏
            scroll_top>1000?show(jQuery('top')):hide(jQuery('top'));
            begin=scroll_top;
            //监听点击
            jQuery('top').onclick=function () {
                //清除定时器
                clearInterval(timer);
                //开启缓动动画
                timer=setInterval(function () {
                    begin=begin+(end-begin)/20;

                    //scrollTo(x,y):把内容滚动到指定的坐标
                    window.scrollTo(0,begin);

                    //清除定时器
                    if(Math.round(begin===end))
                    {
                        clearInterval(timer);
                    }
                },20)
            }
        }
    }
    function jQuery(id){
            return typeof id === "string" ? document.getElementById(id):null;
    }
    function show(obj) {
        return obj.style.display='block';
    }
    function hide(obj) {
        return obj.style.display='none';
    }