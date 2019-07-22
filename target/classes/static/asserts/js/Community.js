window.onload=function () {
    var box = document.getElementById('box');
    var small_box = box.children[0];
    var big_box = box.children[1];
    var mask = small_box.children[1];
    var big_img = big_box.children[0];

    small_box.onmouseover = function () {
        mask.style.display = 'block';
        big_box.style.display = 'block';

        small_box.onmousemove = function (event) {
            var event = event || window.event;
            var pointX = event.clientX - small_box.offsetParent.offsetLeft*0.5 ;
            var pointY = event.clientY - small_box.offsetParent.offsetTop*0.5;

            if (pointX < 0) {
                pointX = 0;
            } else if (pointX >= small_box.offsetWidth - mask.offsetWidth) {
                pointX = small_box.offsetWidth - mask.offsetWidth;
            }
            if (pointY < 0) {
                pointY = 0;
            } else if (pointY >= small_box.offsetHeight - mask.offsetHeight) {
                pointY = small_box.offsetHeight - mask.offsetHeight;
            }
            mask.style.left = pointX + 'px';
            mask.style.top = pointY + 'px';

            big_img.style.left = -pointX / (small_box.offsetWidth / big_box.offsetWidth) + 'px'- mask.offsetWidth * 0.5;
            big_img.style.top = -pointY / (small_box.offsetHeight / big_box.offsetHeight) + 'px'- mask.offsetHeight * 0.5;
        }
    }
    small_box.onmouseout = function () {
        mask.style.display = 'none';
        big_box.style.display = 'none';

    }
}