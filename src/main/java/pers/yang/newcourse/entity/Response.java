package pers.yang.newcourse.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    
    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    public Object data;

}