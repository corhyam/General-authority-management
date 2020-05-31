package com.jier.admin.service;


import com.github.pagehelper.PageInfo;
import com.jier.admin.dao.DeptMapper;
import com.jier.admin.entity.Dept;
import com.jier.admin.entity.DeptExample;
import com.jier.admin.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @author Corhyam
 * @version 1.0
 * @date 2020/5/31 12:36
 */


@Service
public interface DeptService{
    public List<Dept> selectAllDept();
    public boolean addDept(Dept dept);
    public boolean updateDept(Dept dept);
    public boolean deleteDeptByIds(List<Integer> ids);
    public boolean deleteDeptById(Integer id);

}
