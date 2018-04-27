package com.ani.utils.dto.response;
import java.io.Serializable;


public class ResponseMessage implements Serializable{
    private static final long serialVersionUID = -4002424118653998970L;
    private ResponseStateEnum state;
    private Object data;
    private String msg;

    public ResponseMessage() {
    }

    public ResponseMessage(ResponseStateEnum state, Object data, String msg) {
        this.state = state;
        this.data = data;
        this.msg = msg;
    }

    public ResponseStateEnum getState() {
        return state;
    }

    public void setState(ResponseStateEnum state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
