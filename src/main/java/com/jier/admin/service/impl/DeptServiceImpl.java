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
    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> selectAllDept() {
        return deptMapper.selectAllDept();
    }

    @Override
    public int insertSelective(Dept record) {
        return deptMapper.insertSelective(record);
    }

    @Override
    public int deleteById(List<Integer> ids) {
        return deptMapper.updateDeleteById(ids);
    }

    @Override
    public int updateByPrimaryKeySelective(Dept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }

}
