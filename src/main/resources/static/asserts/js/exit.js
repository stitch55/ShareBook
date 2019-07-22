jQuery(function () {
    jQuery("#img_login").mouseenter(function () {
        jQuery(".exit").css("display","block");
    })
    jQuery("#img_login").mouseleave(function () {
        jQuery(".exit").css("display","none");
    })
    jQuery(".exit").mouseenter(function () {
        jQuery(".exit").css("display","block");
    })
    jQuery(".exit").mouseleave(function () {
        jQuery(".exit").css("display","none");
    })
})