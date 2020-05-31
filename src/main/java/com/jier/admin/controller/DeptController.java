package com.jier.admin.controller;

/**
 * @author Corhyam
 * @version 1.0
 * @date 2020/5/31 12:40
 */

import com.jier.admin.entity.Dept;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/dept")
public class DeptController {
    @Resource
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


    @GetMapping("")
    public LayUITable selectAllMenu(){
        List<Dept> depts = deptService.selectAllDept();
        return LayUITable.responseData(0,"success",0,depts);
    }

    @PostMapping("")
    public LayUITable addMenu(Dept dept){
        boolean isAdded = deptService.addDept(dept);
        if(isAdded){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }

    @PutMapping("")
    public LayUITable updateMenu( Dept dept){
        boolean isUpdated =deptService.updateDept(dept);
        if(isUpdated){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }

    @DeleteMapping()
    public LayUITable delMenu(@RequestParam Integer id){

        boolean isDeleted = deptService.deleteDeptById(id);

        if(isDeleted){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    @DeleteMapping("/ids")
    public LayUITable delMenus(@RequestParam(value ="ids") Set<Integer> ids){
        List<Integer> list = new ArrayList<>(ids);
        boolean isDeleted = deptService.deleteDeptByIds(list);
        if(isDeleted){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
}
