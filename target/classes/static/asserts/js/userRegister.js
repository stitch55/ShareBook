 function  Register(id){

       var nickname;
       if(id==1){
            nickname ="彭猪猪";
       }else if(id==2){
           nickname ="蓉蓉";
       }else if(id==3){
           nickname ="蔡蔡";
       }

       var user = {
           "nickname":nickname,
           "number":"123456",
           "password":123456,
           "inspiration":"今天天气真好",
           "photoUrl":"/resource/user/头像.jpg",
           "own_count":0,
           "borrow_count":0
       }
       jQuery.ajax({
           url:"/register",
           type:"post",
           contentType:"application/json",
           data:JSON.stringify(user),
           success:function (data) {
               alert(data.state);
               if(data.state=="success"){
                   //注册成功
                   alert(data.state);
               }else {
                   //注册失败，提示昵称已被人使用
                   alert(data.state);
               }
           },
           fail:function (data) {
               //处理未知错误
               alert("未知错误");
           }
       });

 }