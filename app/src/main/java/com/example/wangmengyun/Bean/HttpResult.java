package com.example.wangmengyun.Bean;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public class HttpResult<T>{
    private int status;

    private String msg;
    private T data;

    public T getData(){
        return data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString(){
        StringBuffer sb =new StringBuffer();

        sb.append("status ="+status+ ",msg = "+msg);

        if(null!=data){
            sb.append(",data= "+data.toString());

        }
        return sb.toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
