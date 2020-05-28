package com.jier.admin.controller;

import com.jier.admin.dao.RoleMenuMapper;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.RoleMenuKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-28 18:06
 **/
@RestController
public class TestController {
    @Resource
    RoleMenuMapper roleMenuMapper;

    @GetMapping("/get")
    public LayUITable get(){
        List<RoleMenuKey> roleMenuKeys = roleMenuMapper.selectByExample(null);

        return LayUITable.responseData(0,"success",0,roleMenuKeys);
    }
}
