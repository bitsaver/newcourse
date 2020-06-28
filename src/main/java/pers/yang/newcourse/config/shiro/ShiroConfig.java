package pers.yang.newcourse.config.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yang.newcourse.config.jwt.JWTAuthcFilter;
import pers.yang.newcourse.config.jwt.JWTRoleFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 配置创建过滤器工厂类
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //自定义拦截器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();

        filterMap.put("jwtAuth",new JWTAuthcFilter());
        filterMap.put("jwtRole",new JWTRoleFilter());

        shiroFilterFactoryBean.setFilters(filterMap);

        //过滤器规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anon | authc | user | perms[操作名] | role[角色名] |jwtroles[角色名]

        filterChainDefinitionMap.put("/newcourse/user/login","anon");

        filterChainDefinitionMap.put("/newcourse/user/**", "jwtRole[admin]");
        filterChainDefinitionMap.put("/newcourse/course/add","jwtRole[teacher]");
        filterChainDefinitionMap.put("/newcourse/question/**","jwtRole[teacher]");
        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/**", "jwtAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    /**
     * 配置安全管理者，用于管理HTTP连接对象
     * @param
     * @return
     */
    @Bean(name= "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myRealm);

        // http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    //将认证和授权操作注入Spring容器中
    @Bean(name = "myRealm")
    public MyRealm myRealm(){
        return new MyRealm();
    }

    //Shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor;
        authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}