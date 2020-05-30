package com.jier.admin.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.User;
import com.jier.admin.service.UserService;
import com.jier.admin.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/toShowUser")
    public String toShowUserLayui() {
        return "user/showUser";
    }

    @RequestMapping("/showUser")
    @ResponseBody
    public LayUITable showUser(int page, int limit) {
        //开始查询
        PageInfo<User> pageInfo = userServiceImpl.selectAllUser(page, limit);
        LayUITable layUiTable = new LayUITable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        //设置分页之后的返回值
        layUiTable.setCount(pageInfo.getTotal());
        layUiTable.setData(pageInfo.getList());
        return layUiTable;
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(User userInfo){
        int i = userServiceImpl.insertSelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.saveSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.saveFailMsg);
        }
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object editUser(User user){
        User user1 = new User();
        System.out.println(user);
        user1.setUserName("xiaopag");
        user1.setUserId(5);
        int i = userServiceImpl.updateByPrimaryKeySelective(user);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        System.out.println(map);
        return map;
    }

    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        System.out.println(list);
        int i = userServiceImpl.delUserByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Object delUser( @RequestParam(value = "userId") Integer  userId){
        System.out.println(userId);
        int i = userServiceImpl.deleteByPrimaryKey(userId);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }

}
