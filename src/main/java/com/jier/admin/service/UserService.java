package com.jier.admin.service;

import com.github.pagehelper.PageInfo;
import com.jier.admin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService{
    PageInfo<User> selectAllUser(int page, int limit);
    int delUserByID(List<String> ids);
    int insertSelective(User record);
    int deleteByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User userInfo);
}
