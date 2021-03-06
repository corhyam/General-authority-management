package com.jier.admin.controller;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.LayUITable;
import com.jier.admin.entity.LayUiTree;
import com.jier.admin.entity.Menu;
import com.jier.admin.entity.SelectTree;
import com.jier.admin.groupValidation.AddMenu;
import com.jier.admin.groupValidation.UpdateMenu;
import com.jier.admin.service.MenuService;
import com.jier.admin.service.ShiroService;
import com.jier.admin.util.SelectTreeUtils;
import com.jier.admin.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Resource
    ShiroService shiroService;

    /** 
    * @Description: 树状 
    * @Param:  
    * @return:  
    * @Author: Mr.liu
    * @Date: 2020/5/29 
    */
    @GetMapping("/tree")
    public List<LayUiTree> selectAllMenuTree(){
        List<LayUiTree> menus = menuService.selectAllMenu();
        return menus;
    }
    @GetMapping("/treeSelect")
    public List<SelectTree> selectTreeSelect(){
       return  menuService.selectTreeMenu();
    }
    /** 
    * @Description: 查询全部菜单 
    * @Param:  
    * @return:  
    * @Author: Mr.liu
    * @Date: 2020/5/29 
    */
    @GetMapping("/select")
    public LayUITable selectAllMenu(){
        List<Menu> menus = menuService.selectMenu();
        return LayUITable.responseData(0,"success",0,menus);
    }
    /** 
    * @Description: 添加菜单
    * @Param:  
    * @return:  
    * @Author: Mr.liu
    * @Date: 2020/5/29 
    */
    @PostMapping("/add")
    public LayUITable addMenu(@Validated({AddMenu.class}) Menu menu){
        boolean isAdded = menuService.addMenu(menu);
        if(isAdded){
            shiroService.updatePermission();
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    /**
    * @Description: 更新菜单
    * @Param:
    * @return:
    * @Author: Mr.liu
    * @Date: 2020/5/29
    */
    @PutMapping("/edit")
    public LayUITable updateMenu(@Validated({UpdateMenu.class}) Menu menu){
        boolean isUpdated = menuService.updateMenu(menu);
        if(isUpdated){
            shiroService.updatePermission();
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    /** 
    * @Description: 批量删除菜单 
    * @Param:  
    * @return:  
    * @Author: Mr.liu
    * @Date: 2020/5/30 
    */
    @DeleteMapping("/ids")
    public LayUITable delMenus(@RequestParam(value ="ids")Set<Integer> ids){
        List<Integer> list = new ArrayList<>(ids);
        boolean isDeleted = menuService.deleteMenuByIds(list);
        if(isDeleted){
            shiroService.updatePermission();
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
    /**
    * @Description: 删除菜单
    * @Param:
    * @return:
    * @Author: Mr.liu
    * @Date: 2020/5/29
    */
    @DeleteMapping("/id")
    public LayUITable delMenu(@RequestParam Integer id){

        boolean isDeleted = menuService.deleteMenuById(id);

        if(isDeleted){
            shiroService.updatePermission();
            return LayUITable.responseData(200,"success");
        }
        return LayUITable.responseData(0,"failure");
    }
   
}
