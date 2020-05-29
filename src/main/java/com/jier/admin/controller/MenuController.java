package com.jier.admin.controller;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.LayUiTree;
import com.jier.admin.entity.Menu;
import com.jier.admin.groupValidation.AddMenu;
import com.jier.admin.groupValidation.UpdateMenu;
import com.jier.admin.service.MenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-28 19:02
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    @GetMapping("/tree")
    public List<LayUiTree> selectAllMenuTree(){
        List<LayUiTree> menus = menuService.selectAllMenu();
        return menus;
    }
    @Resource
    MenuMapper menuMapper;
    @GetMapping("/select")
    public LayUITable selectAllMenu(){
        List<Menu> menus = menuMapper.selectByExample(null);
        return LayUITable.responseData(0,"success",0,menus);
    }
    @PostMapping("")
    public LayUITable addMenu(@Validated({AddMenu.class}) Menu menu){
        boolean isAdded = menuService.addMenu(menu);
        if(isAdded){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    @PutMapping("")
    public LayUITable updateMenu(@Validated({UpdateMenu.class}) Menu menu){
        boolean isUpdated = menuService.updateMenu(menu);
        if(isUpdated){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    @DeleteMapping()
    public LayUITable delMenu(@RequestParam Integer id){

        boolean isDeleted = menuService.deleteMenuById(id);

        if(isDeleted){
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
}
