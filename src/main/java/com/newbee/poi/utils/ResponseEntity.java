package com.newbee.poi.utils;

import org.springframework.http.HttpStatus;

/**
 * Created by Major on 2016/11/14.
 */
public class ResponseEntity<T>{
    private int flag;
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseEntity() {
    }

    /**
     * 返回结果
     * @param flag 操作状态标识：0——成功，1——失败
     * @param message 操作提示信息
     * @param data 返回的数据
     * @param status 操作状态提示
     * @return ResponseEntity<T>
     */
    public ResponseEntity<T> response(int flag, String message, Object data,HttpStatus status){
        setFlag(flag);

        if (status==null)
            setStatus(HttpStatus.OK);
        else
            setStatus(status);

        setMessage(message);
        setData(data);
        return this;
    }

    public int getFlag() {
        return flag;
    }

    private void setFlag(int flag) {
        this.flag = flag;
    }

    public HttpStatus getStatus() {
        return status;
    }

    private void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }
}
