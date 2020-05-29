package com.jier.admin.controller;

import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.Role;
import com.jier.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
