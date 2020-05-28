package com.jier.admin.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: mybatis
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-25 16:58
 **/
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/show","authc");
        filterMap.put("/login","anon");
        filterMap.put("/query","perms[my-user:add]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置跳转登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");


        return shiroFilterFactoryBean;


    }
    //DefaultWebSecurityManager  2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }
    //创建Realm对象  1
    /*
    * 用户认证授权处理Realm   MyRealm extends AuthorizingRealm
    * */

    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        //myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    /*
    * 密码比较
    * */
/*    @Bean
    public CredentialsMatcher credentialsMatcher(){

        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //设置加密算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        credentialsMatcher.setHashIterations(2);
        return new Md5CredentialsMatcher();
    }*/
}
