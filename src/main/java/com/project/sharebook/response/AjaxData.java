package com.project.sharebook.response;
//封装向前端传递的数据
public class AjaxData {
    private String state;
    private Object data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static AjaxData create(Object data){
        return AjaxData.create(data,"success");
    }
    public static AjaxData create(Object data,String state){
        AjaxData ajaxData = new AjaxData();
       ajaxData.setData(data);
       ajaxData.setState(state);
       return ajaxData;
    }

}
