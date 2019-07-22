package com.project.sharebook.error;

public enum EmBusinessError implements CommonError {
    //通用错误码：例如0001 表示参数不合法，但是参数不合法也有很多类型，此时就可以通过setErrorMsg 修改
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    //用户不存在
    USER_NOT_EXIST(20001,"用户不存在 "),
    USER_FIND_FAIL(20001,"用户信息查找失败"),
    //想要自定义的错误信息都可以在这里进行定制
    //未知错误
    UNKNOWN_ERROR(10002,"未知错误"),
    //登录失败
    LOGIN_FAIL(20002,"账户或密码错误"),
    COMMENT_NOT_EXIST(20003,"该本书的评论查找失败"),
    BOOK_NOT_EXIST(20004,"书籍信息查找失败"),
    NO_FOCUSRELAYIONSHIP(20004,"该用户暂时没有进行任何关注"),
    PLEASE_LOGIN_FIRST(30001,"请先登录")
    ;

    private int errCode;
    private String errMsg;
    private EmBusinessError(int errCode, String errMsg){
        this.errMsg = errMsg;
        this.errCode = errCode;
    }
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {  //可以改动errMsg
        this.errMsg = errorMsg;
        return this;
    }
}
