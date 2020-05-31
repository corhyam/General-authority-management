package com.jier.admin.controller;

/**
 * @author Corhyam
 * @version 1.0
 * @date 2020/5/31 12:40
 */

import com.alibaba.fastjson.JSON;
import com.jier.admin.entity.Dept;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.User;
import com.jier.admin.service.DeptService;
import com.jier.admin.util.MyConstants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping("/toShowDept")
    public String toShowDept(){
        return "dept/showDept";
    }

    @RequestMapping("/showDept")
    @ResponseBody
    public LayUITable showDept(){
        List<Dept> DeptList = deptService.selectAllDept();
        LayUITable layUITable=new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("返回消息");
        layUITable.setData(DeptList);
        return layUITable;
    }

    @RequestMapping("/saveDept")
    @ResponseBody
    public Object savedept(Integer deptId,String deptName,Integer orderNum,String status,String deptFlag){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept=new Dept();
        dept.setDeptId(deptId);
        dept.setDeptName(deptName);
        dept.setOrderNum(orderNum);
        dept.setStatus(status);
        dept.setDelFlag(deptFlag);
        dept.setCreateBy(user.getLoginName());
        dept.setCreateTime(new Date());
        if (status==null)
            status = "0";
        dept.setStatus(status);
        user=null;
        int i = deptService.insertSelective(dept);
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
    @RequestMapping("/delDept")
    @ResponseBody
    public Object delRole(@RequestParam(value = "ids")String ids){
        List<Integer> list = (List<Integer>) JSON.parse(ids);
        int i = deptService.deleteById(list);
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

    @RequestMapping("/editDept")
    @ResponseBody
    public Object editdept(Integer deptId,String deptName,Integer orderNum,String status,String deptFlag){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept=new Dept();
        dept.setDeptId(deptId);
        dept.setDeptName(deptName);
        dept.setOrderNum(orderNum);
        dept.setStatus(status);
        dept.setDelFlag(deptFlag);
        dept.setUpdateBy(user.getLoginName());
        dept.setUpdateTime(new Date());
        if (status==null)
            status = "0";
        dept.setStatus(status);
        user=null;
        int i = deptService.updateByPrimaryKeySelective(dept);
        Map map = new LinkedHashMap();
        if(i>0){

            map.put("code", MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else{
            map.put("code", MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }

        return map;

    }
}
