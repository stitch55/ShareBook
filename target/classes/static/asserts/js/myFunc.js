//获取id
function $(id){
        return typeof id === "string" ? document.getElementById(id):null;
}
//scroll封装函数
function scroll() {
    if(window.pageYOffset!=null)
    {
        return{
            top:window.pageYOffset,
            left:window.pageXOffset
        }
    }
    else if(document.compatMode === 'CSS1Compat')
    {
        //遵循w3c
        return{
            top:document.documentElement.scrollTop,
            left:document.documentElement.scrollLeft
        }
    }
    return{
        top:document.body.scrollTop,
        left:document.body.scrollLeft
    }
}

//匀速动画函数
//元素[object]:ele    目标值[number]:target  步长[number]:speed
function constant(obj,target,speed) {
    //清除定时器
    clearInterval(obj.timer);

    //判断方向
    var dir=obj.offsetLeft<target?speed:-speed;

    //设置定时器
    obj.timer=setInterval(function () {
        obj.style.left=obj.offsetLeft+dir+'px';

        // console.log(Math.abs(target - obj.offsetLeft));
        if(Math.abs(target - obj.offsetLeft)<Math.abs(dir))
        {
            clearInterval(obj.timer);
            obj.style.left=target+'px';
            console.log(obj.offsetLeft);
        }
    },20)
}

//获取用户选中的内容
function getSelectedText() {
    var selectedText;
    if(window.getSelection)
    {
        selectedText=window.getSelection().toString();
    }
    else
    {
        selectedText=document.selection.createRange().text;
    }
}

//获取屏幕的高度和宽度
function client() {
    //IE9和最新浏览器
    if(window.innerWidth)
    {
        return {
            width:window.innerWidth,
            height:window.innerHeight
        }
    }
    else if(document.compatMode==="CSS1Compat")
    {
        //w3c
        return {
            width:document.documentElement.clientWidth,
            height:document.documentElement.clientHeight,
        }
    }
    return{
        width:document.body.clientWidth,
        height:document.body.clientHeight,
    }
}
//阻止事件冒泡
function stopBubble(event) {
    if(event && event.stopImmediatePropagation)
    {
        //W3C
        event.stopPropagation();
    }
    else{
        event.cancelBubble=true;
    }
}

//JS获取CSS属性
//obj:对象
//attr[string]:属性名
function getStyleAttr(obj,attr) {
    if(obj.currentStyle)
    {
        return obj.currentStyle[attr];
    }
    else{
        return window.getComputedStyle(obj,null)[attr];
    }
}

//封装缓动动画函数
function buffer(obj,json,fn ){
    clearInterval(obj.timer);

    var begin=0,target=0,speed=0;
    obj.timer=setInterval(function () {
        //标识是否清楚定时器
        var flag=true;
        for(var key in json)
        {
            // if("opacity" === k)  //透明度
            // {
            //     begin=Math.round(parseInt(getStyleAttr(obj,key))*100) || 100;
            //     target=parseInt(json[key]*100);
            // }
            if("scrollTop" === key)
            {
                begin=obj.scrollTop;
                target=parseInt(json[key]);
            }
            else{
                //获取初始值
                begin=parseInt(getStyleAttr(obj,key)) || 0;
                target=parseInt(json[key]);
            }

            //求助步长
            speed=(target-begin)*0.2;

            //判断是否向上取整
            speed=(target>begin) ? Math.ceil(speed):Math.floor(speed);
            //让盒子动起来
            // if("opacity" === k)
            // {
            //     //w3c浏览器
            //     obj.style.opacity=(begin+speed)/100;
            //     //IE浏览器
            //     obj.style.filter='alpha(opacity:'+(begin+speed)+')';
            // }
            if("scrollTop" === key){
                obj.scrollTop=begin+speed;
            }
            else{
                obj.style[key]=begin+speed+'px';
            }

            // obj.innerText=begin;

            //判断清徐定时器的条件
            if(begin!==target) {
                flag = false;

                //判断有没有回调函数
                if(fn)
                {
                    fn();
                }
            }
        }

        //清楚定时器
        if(flag)
        {
            clearInterval(obj.timer);
        }
    },20)
}

//闭包场景-函数节流
function throttle(fn,delay) {
    var timer=null;
    return function () {
        clearInterval(timer);
        timer=setInterval(fn,delay);
    }
}