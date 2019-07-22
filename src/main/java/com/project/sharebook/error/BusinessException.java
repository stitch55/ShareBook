package com.project.sharebook.error;
//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {
    //强关联一个CommonError,方便进行使用
    private CommonError commonError;
    //构造函数
    //直接接收commonError
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }
    //可以需改errMsg
    public BusinessException(CommonError commonError, String Msg){
        super();
        this.commonError = commonError;
        commonError.setErrorMsg(Msg);
    }
    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
