package com.jier.admin.util;



import com.jier.admin.entity.Menu;
import com.jier.admin.entity.SelectTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-06-01 00:18
 **/
public class SelectTreeUtils {
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<SelectTree> getChildPerms(List<Menu> list, int parentId)
    {
        List<SelectTree> returnList = new ArrayList<SelectTree>();
        for (Menu menu : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if(menu.getParentId()==parentId){
                SelectTree tree = fromMenuToTree(menu);
                //开始递归，把所有菜单和当前菜单放入
                recursionFn(list, tree);
                returnList.add(tree);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<Menu> list, SelectTree t)
    {
        // 得到t的子节点列表
        List<SelectTree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SelectTree tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SelectTree> it = childList.iterator();
                while (it.hasNext())
                {
                    SelectTree n =  it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<SelectTree> getChildList(List<Menu> list, SelectTree t)
    {

        List<SelectTree> tlist = new ArrayList<SelectTree>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu menu = (Menu) it.next();
            if (menu.getParentId() == t.getId())
            {
                SelectTree tree = fromMenuToTree(menu);
                tlist.add(tree);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */

    private static boolean hasChild(List<Menu> list, SelectTree t)
    {
        int size = getChildList(list, t).size();
        if(size>0){
            return true;
        }else
        {
            return false;
        }
    }

    /**
     * 将menu对象转换成tree对象
     * @param menu
     * @return
     */
    private static SelectTree fromMenuToTree(Menu menu){
        //构造tree对象
        SelectTree tree= new SelectTree();
        tree.setId(menu.getMenuId());
        tree.setName(menu.getMenuName());
        tree.setChecked(false);
        return tree;
    }

}
