package com.jier.admin.service;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.LayUiTree;
import com.jier.admin.entity.Menu;
import com.jier.admin.entity.MenuExample;
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
 * @create: 2020-05-28 18:21
 **/
public interface MenuService {
    public List<LayUiTree> selectAllMenu();
    public List<Menu> selectMenu();
    public boolean addMenu(Menu menu);
    public boolean updateMenu(Menu menu);
    public boolean deleteMenuByIds(List<Integer> ids);
    public boolean deleteMenuById(Integer id);
    List<LayUiTree>  selectAllMenuByName(String loginName);
}
