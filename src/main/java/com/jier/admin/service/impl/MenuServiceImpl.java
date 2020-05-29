package com.jier.admin.service.impl;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.LayUiTree;
import com.jier.admin.entity.Menu;
import com.jier.admin.entity.MenuExample;
import com.jier.admin.service.MenuService;
import com.jier.admin.util.TreeUtils;
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

    public boolean addMenu(Menu menu){
        menu.setCreateTime(new Date());
        menu.setCreateBy("admin");
        menu.setUpdateBy("admin");
        menu.setUpdateTime(new Date());
        int i=menuMapper.insert(menu);
        if(i!=1){
            return false;
        }
        return true;
    }
    public boolean updateMenu(Menu menu){
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
