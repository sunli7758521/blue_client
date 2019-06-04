package com.msj.goods.config;

import com.msj.goods.shiro.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类ShiroConfig的功能描述:
 * Shiro配置
 * @auther hxy
 * @date 2017-11-15 21:50:12
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
//    public SessionManager sessionManager(CachingShiroSessionDao sessionDAO){
    public SessionManager sessionManager(){
//        sessionDAO.setPrefix("shiro-session:");
        //注意中央缓存有效时间要比本地缓存有效时间长
//        sessionDAO.setSeconds(10);
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionDAO(sessionDAO);
//        sessionManager.setSessionDAO(new MemorySessionDAO());
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/user/unauth");     //无权限或者session超时跳转路径转路径，此处需要定义统一处理接口
        shiroFilter.setUnauthorizedUrl("/");

        //shiro不拦截资源
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/druid/**", "anon");
        //登录
        filterMap.put("/login", "anon");
            //swagger文档地址
        filterMap.put("/swagger-ui.html", "anon");
        //swagger静态资源
        filterMap.put("/webjars/**", "anon");
        //swagger静态资源
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/permission", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/lovePraise/*","anon");

       /* filterMap.put("/company/**", "anon");*/
        filterMap.put("/empp/**", "anon");
        filterMap.put("/task/**", "anon");

        filterMap.put("/user/**", "anon");
        filterMap.put("/upload/**", "anon");
        //        积分申诉
        filterMap.put("/complaint/**", "anon");
        filterMap.put("/leader/leaderManageIntegral", "anon");
        // 水平考核科目表
        filterMap.put("/Levelass/**", "anon");

        filterMap.put("/test/**", "anon");


        //除了上述三个全部需要认证访问
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
