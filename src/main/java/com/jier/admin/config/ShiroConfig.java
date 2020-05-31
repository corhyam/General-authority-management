package com.jier.admin.config;

import com.jier.admin.dao.MenuMapper;
import com.jier.admin.entity.Menu;
import com.jier.admin.util.MyConstants;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-25 16:58
 **/
@Configuration
public class ShiroConfig {
    @Resource
    MenuMapper menuMapper;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/login","anon");

        //自定义加载权限资源关系

        List<Menu> resourcesList =menuMapper.selectByExample(null);
        for(Menu menu:resourcesList){

            if (!menu.equals("#")) {
                String permission = "perms[" + menu.getPerms()+ "]";
                filterMap.put(menu.getUrl(),permission);
            }
        }
        filterMap.put("/**","authc");
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
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher= new HashedCredentialsMatcher();
        //使用md5加密
        credentialsMatcher.setHashAlgorithmName(MyConstants.algorithmName);
        //加密1000次
        credentialsMatcher.setHashIterations(MyConstants.hashIterations);
        return credentialsMatcher;
    }
}
