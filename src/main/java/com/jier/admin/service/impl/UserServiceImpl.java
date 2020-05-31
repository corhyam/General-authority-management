package com.jier.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jier.admin.config.ShiroUtil;
import com.jier.admin.dao.UserMapper;
import com.jier.admin.entity.User;
import com.jier.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
        @Resource
        private UserMapper userMapper;
        @Override
        public PageInfo<User> selectAllUser(int page, int limit) {
            //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
            PageHelper.startPage(page, limit);
            List<User> myUserInfos = userMapper.selectAllUser();
            //结束分页,pageInfo封装了分页之后所有数据
            PageInfo<User> pageInfo = new PageInfo(myUserInfos);
            return pageInfo;
        }

/*        @Override
        public User selectUserByUsername(String username) {

            return userMapper.selectUserByUsername(username);
        }*/

        @Override
        public int insertSelective(User record) {
            //次处要进行密码加盐加密
            String salt = UUID.randomUUID().toString();
            String message = record.getPassword();
            String encryption = ShiroUtil.encryptionBySalt(salt, message);
            record.setPassword(encryption);
            record.setSalt(salt);
            return userMapper.insertSelective(record);
        }

        @Override
        public int delUserByID(List<String> ids) {
            return userMapper.delUserByID(ids);
        }

/*        @Override
        public int insertSelective(User record) {
            return userMapper.insertSelective(record);
        }*/

        @Override
        public int deleteByPrimaryKey(Integer userId) {
            return userMapper.deleteByPrimaryKey(userId);
        }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

}
