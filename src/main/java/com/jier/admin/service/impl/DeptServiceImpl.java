package com.jier.admin.service.impl;

import com.jier.admin.dao.DeptMapper;
import com.jier.admin.entity.Dept;
import com.jier.admin.entity.DeptExample;
import com.jier.admin.service.DeptService;
import com.sun.org.apache.bcel.internal.generic.DMUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;
import java.util.List;

/**
 * @author Corhyam
 * @version 1.0
 * @date 2020/5/31 12:38
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptMapper deptMapper;
    @Override
    public List<Dept> selectAllDept() {
        return deptMapper.selectAllDept();
    }

    @Override
    public boolean addDept(Dept dept) {
        dept.setCreateTime(new Date());
        dept.setCreateBy("admin");
        dept.setUpdateBy("admin");
        dept.setUpdateTime(new Date());
        int i=deptMapper.insert(dept);
        if(i!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDept(Dept dept) {
        dept.setUpdateTime(new Date());
        int i=deptMapper.updateByPrimaryKeySelective(dept);
        if(i!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDeptByIds(List<Integer> ids) {
        DeptExample deptExample=new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andDeptIdIn(ids);
        int i=deptMapper.deleteByExample(deptExample);
        System.out.println(i);
        if(i!=ids.size()){
            throw  new RuntimeException("异常，未能全部删除，回滚");
        }
        return true;
    }

    @Override
    public boolean deleteDeptById(Integer id) {
        DeptExample deptExample=new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andDeptIdEqualTo(id);
        int i=deptMapper.deleteByExample(deptExample);
        if(i!=1){
            return false;
        }
        return true;
    }
}
