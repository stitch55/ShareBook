jQuery(function () {
    //搜索
    jQuery('#sear').click(function(){
        var txt = jQuery('#ser').val();
        if(txt.length!=0)
        {
            window.location.href="/searchBook?data="+txt;
        }


    })
})