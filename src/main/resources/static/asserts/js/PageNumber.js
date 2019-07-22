//完成分页
jQuery(function () {
    jQuery("#PageNumber").click(function () {
        var NowPage = jQuery("#NowPage").text();
        var  text = jQuery(".PageNumber").attr("id");
        var type = jQuery("#TypeNameCh").text();
       var  pageCount = jQuery("#pageCount").text();
        var types  = new Array("小说","科技教育","文艺","历史文化","经济/教育");
        //判断当前类别是哪一个
        for(var i=0;i<5;i++){
            if(type==types[i]){
                type=i+1;
            }
        }

        var page;
        if(text=="Page1"){
            page=1;
        }else if (text=="Page2") {
            page=NowPage-1;
        }else if(text=="Page3"){
            page =parseInt(NowPage)+1;
        }else if(text=="Page4"){
            page=pageCount;
        }

        jQuery("#TypeName").load("/TypeName",{
            "type":type,
            "page":page
        })
        jQuery("#MoreBooks").load("/MoreBooks",{
            "type":type,
            "page":page
        })
        jQuery("#PageNumber").load("/pageNumber",{
            "type":type,
            "page":page
        })
    })
})