package com.jier.admin.config;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.dao.RoleMenuMapper;
import com.jier.admin.dao.UserMapper;
import com.jier.admin.dao.UserRoleMapper;
import com.jier.admin.entity.*;
import com.jier.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
;import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mybatis
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-25 16:58
 **/

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Resource
    MenuMapper menuMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    RoleMenuMapper roleMenuMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取数据库中查得的用户信息  并添加其权限
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        //查出用户具有角色id
        UserRoleExample userRoleExample=new UserRoleExample();
        UserRoleExample.Criteria criteria1 = userRoleExample.createCriteria();
        criteria1.andUserIdEqualTo(Long.valueOf(user.getUserId()));
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        List<Long> roleIds=new ArrayList<>();
        for (UserRole userRole:userRoles
        ) {
            roleIds.add(userRole.getRoleId());
        }
        //查出角色对应权限ids
        RoleMenuExample roleMenuExample=new RoleMenuExample();
        RoleMenuExample.Criteria criteria2 = roleMenuExample.createCriteria();
        criteria2.andRoleIdIn(roleIds);
        List<RoleMenuKey> roleMenuKeys = roleMenuMapper.selectByExample(roleMenuExample);
        List<Integer> menuIds=new ArrayList<>();
        for (RoleMenuKey roleMenuKey:roleMenuKeys
        ) {
            menuIds.add(roleMenuKey.getMenuId().intValue());
        }
        //查出用户具有的菜单
        MenuExample example=new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdIn(menuIds);
        List<Menu> resourcesList =menuMapper.selectByExample(example);
        //将用户权限添加至内存
        for(Menu menu:resourcesList){
            info.addStringPermission(menu.getPerms());
        }
/*        info.getStringPermissions().stream().forEach((s)->{
            System.out.println(s);
        });*/


        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        //获取当前用户
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        //登录验证分两个步骤，步骤一查询用户是否存在
        String username=usernamePasswordToken.getUsername();
        User userInfo = userService.selectUserByUsername(username);
        if(null==userInfo){
            return null;
        }
        //步骤二，查询密码是否正确
        //数据库中的密码
        String password=userInfo.getPassword();
        //Object principal, Object credentials, String realmName
        /**
         *  * @param principal         the 'primary' principal associated with the specified realm.
         *      * @param hashedCredentials the hashed credentials that verify the given principal.
         *      * @param credentialsSalt   the salt used when hashing the given hashedCredentials
         *      * @param realmName         the realm from where the principal and credentials were acquired.
         */
        String salt = userInfo.getSalt();
        ByteSource byteSource=ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo= new SimpleAuthenticationInfo(userInfo,password,byteSource,getName());
        return simpleAuthenticationInfo;
    }

}
