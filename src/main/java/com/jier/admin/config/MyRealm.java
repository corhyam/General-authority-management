package com.jier.admin.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
;

/**
 * @program: mybatis
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-25 16:58
 **/

public class MyRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("my-user:add");
        //获取数据库中查得的用户信息  并添加其权限
        Subject subject = SecurityUtils.getSubject();
        //UserInfo userInfo=(UserInfo) subject.getPrincipal();


        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        //获取当前用户

        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        //数据库连接查询
        String name="admin";
        String password="123";
      /*  UserInfoExample userInfoExample=new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(usernamePasswordToken.getUsername());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        if(userInfos==null||userInfos.size()==0){
            return null;
        }
        UserInfo userInfo=userInfos.get(0);
        if (!usernamePasswordToken.getUsername().equals(userInfo.getUsername())){
            return null;
        }*/
        if (!usernamePasswordToken.getUsername().equals(name)){
            return null;
        }
        return new SimpleAuthenticationInfo("",password,"");
    }

}
