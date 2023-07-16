package com.cy.store.util;

import java.io.Serializable;

//状态码，状态描述信息，数据。这部分功能封装一个类中，将这类作为方法返回值，返回给前端浏览器
public class JsonResult<E> implements Serializable {
    //定义状态码
    private Integer state;
    //描述信息
    private String message;

    public JsonResult() {
    }
    public JsonResult(Throwable e){
        this.message = e.getMessage();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    //数据
    private E data;

}
