package com.cspup.tupian.common;

/**
 * @author csp
 * @date 2022/3/13 15:32
 * @description
 */

public class R<T> {

    private int status;
    private String message="ok";
    private T data;

    private R(int status){
        this.status=status;
    }

    private R(int status, String message){
        this.status=status;
        this.message=message;
    }
    private R(int status, T data){
        this.status=status;
        this.data=data;
    }
    private R(int status, String message, T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }


    public static <T> R<T> ok(){
        return new R<>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> R<T> ok(T data){
        return new R<>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> R<T> ok(String message,T data){
        return new R<>(ResponseCode.SUCCESS.getCode(),message,data);
    }
    public static <T> R<T> ok(String message){
        return new R<>(ResponseCode.SUCCESS.getCode(),message);
    }
    public static <T> R<T> error(){
        return new R<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> R<T> error(String message){
        return new R<>(ResponseCode.ERROR.getCode(),message);
    }
    public static <T> R<T> error(int errCode,String message){
        return new R<>(errCode,message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
