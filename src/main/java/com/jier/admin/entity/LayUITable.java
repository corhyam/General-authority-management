package com.jier.admin.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 16:55
 * @Version 1.0
 */
@Data
public class LayUITable <T>{
    private int code;
    private String msg;
    private long count;
    private T data;

    public static <T> LayUITable responseData(int code,String msg,long count,T data){
        LayUITable layUITable=new LayUITable();
        layUITable.setCode(code);
        layUITable.setMsg(msg);
        layUITable.setData(data);
        layUITable.setCount(count);
        return layUITable;
    }
    public static <T> LayUITable responseData(int code,String msg){
        LayUITable layUITable=new LayUITable();
        layUITable.setCode(code);
        layUITable.setMsg(msg);
        return layUITable;
    }
    public static <T> LayUITable responseDataSuccess(){
        LayUITable layUITable=new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("success");
        return layUITable;
    }
}
