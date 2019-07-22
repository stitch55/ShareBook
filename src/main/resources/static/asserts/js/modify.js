$(function () {
    $("#like").click(function () {
        $(".show p").html("点赞数");
    })
    $("#date").click(function () {
        $(".show p").html("最新");
    })

    //点赞
    $(".like_img").click(function () {
        // 未实现，一个用户只能赞一次
        $(this).attr("src","image/点赞后.png");
        var num=$(".number").html();
        num++;
        $(".number").html(num);
    })
})
