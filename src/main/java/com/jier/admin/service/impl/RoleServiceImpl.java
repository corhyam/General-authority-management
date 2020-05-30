package com.jier.admin.service.impl;

import com.jier.admin.dao.RoleMapper;
import com.jier.admin.entity.Role;
import com.jier.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public int insertSelective(Role record) {

        return roleMapper.insertSelective(record);
    }

    @Override
    public int deleteById(List<Integer> ids) {
        return roleMapper.deleteById(ids);
    }
}
