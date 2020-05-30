package com.jier.admin.controller;

import com.alibaba.fastjson.JSON;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.Role;
import com.jier.admin.service.RoleService;
import com.jier.admin.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleServiceImpl;

    @RequestMapping("/toShowRole")
    public String toShowRole(){
        return "role/showRole";
    }
    @RequestMapping("/showRole")
    @ResponseBody
    public LayUITable showRole(){
        List<Role> roleList = roleServiceImpl.selectAllRole();
        LayUITable layUITable=new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("返回消息");
        layUITable.setData(roleList);
        return layUITable;
    }
    @RequestMapping("/saveRole")
    @ResponseBody
    public Object saveRole(String roleName,String roleKey,Integer roleSort,String status){
        Role role=new Role();
        role.setRoleName(roleName);
        role.setRoleKey(roleKey);
        role.setRoleSort(roleSort);
        if (status==null)
            status = "0";
        role.setStatus(status);
        int i = roleServiceImpl.insertSelective(role);
        Map map = new LinkedHashMap();
        if(i>0){

            map.put("code", MyConstants.successCode);
            map.put("message",MyConstants.saveSuccessMsg);
        }else{
            map.put("code", MyConstants.failCode);
            map.put("message",MyConstants.saveFailMsg);
        }

        return map;

    }
    @RequestMapping("/delRole")
    @ResponseBody
    public Object delRole(@RequestParam(value = "ids")String ids){
        List<Integer> list = (List<Integer>) JSON.parse(ids);
        int i = roleServiceImpl.deleteById(list);
        Map map = new LinkedHashMap();
        if(i>0){

            map.put("code", MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else{
            map.put("code", MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }

        return map;
    }

}
