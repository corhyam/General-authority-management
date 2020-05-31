package com.jier.admin.service.impl;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.*;
import com.jier.admin.service.MenuService;
import com.jier.admin.util.SelectTreeUtils;
import com.jier.admin.util.TreeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-29 17:02
 **/
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMapper;
    @Override
    public List<LayUiTree> selectAllMenuByName(String loginName) {
        //查询所有的菜单
        List<Menu> menus = menuMapper.selectAllMenuByName(loginName);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询所有的菜单，并组装成tree格式的
     * @return
     */
    @Override
    public List<LayUiTree> selectAllMenu() {
        //查询所有的菜单
        List<Menu> menus = menuMapper.selectByExample(null);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }

    @Override
    public List<Menu> selectMenu() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public List<SelectTree> selectTreeMenu() {
        List<Menu> menus = menuMapper.selectByExample(null);
        //并组装成tree格式的
        return SelectTreeUtils.getChildPerms(menus, 0);
    }

    public boolean addMenu(Menu menu){
        menu.setCreateTime(new Date());
        Subject subject = SecurityUtils.getSubject();
        if(subject==null){
            throw new RuntimeException("未能获取当前用户，未登录");
        }
        User user=(User) subject.getPrincipal();
        menu.setCreateBy(user.getLoginName());
        menu.setUpdateBy(user.getLoginName());
        menu.setUpdateTime(new Date());
        int i=menuMapper.insert(menu);
        if(i!=1){
            return false;
        }
        return true;
    }
    public boolean updateMenu(Menu menu){
        Subject subject = SecurityUtils.getSubject();
        if(subject==null){
            throw new RuntimeException("未能获取当前用户，未登录");
        }
        User user=(User) subject.getPrincipal();
        menu.setUpdateBy(user.getLoginName());
        menu.setUpdateTime(new Date());
        int i=menuMapper.updateByPrimaryKeySelective(menu);
        if(i!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMenuByIds(List<Integer> ids) {
        MenuExample menuExample=new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andMenuIdIn(ids);
        int i=menuMapper.deleteByExample(menuExample);
        System.out.println(i);
        if(i!=ids.size()){
            throw  new RuntimeException("异常，未能全部删除，回滚");
        }
        return true;
    }
    @Override
    public boolean deleteMenuById(Integer id){
        MenuExample menuExample=new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andMenuIdEqualTo(id);
        int i=menuMapper.deleteByExample(menuExample);
        if(i!=1){
            return false;
        }
        return true;
    }
}
