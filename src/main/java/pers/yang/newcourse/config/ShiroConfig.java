package pers.yang.newcourse.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        filterMap.put("jwt",new JwtFilter());
        filterMap.put("jwtroles",new RoleFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //过滤器规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anon | authc | user | perms[操作名] | role[角色名] |jwtroles[角色名]
        filterChainDefinitionMap.put("/login","anon");

        filterChainDefinitionMap.put("/newcourse/user/*","authc");
//        filterChainDefinitionMap.put("/newcourse/user/*","jwtroles[admin]");

        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 配置安全管理者，用于管理HTTP连接对象
     * @param
     * @param subjectFactory
     * @return
     */
    @Bean(name= "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm
            ,@Qualifier("subjectFactory") SubjectFactory subjectFactory){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        //1.配置默认Session存储为false
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        //2.交给securityManager进行管理
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);
        defaultWebSecurityManager.setSubjectFactory(subjectFactory);

        //设置UserRealm
        defaultWebSecurityManager.setRealm(myRealm);

        return defaultWebSecurityManager;
    }

    //将认证和授权操作注入Spring容器中
    @Bean(name = "myRealm")
    public MyRealm myRealm(){
        return new MyRealm();
    }

    //设置无状态session
    @Bean(name = "subjectFactory")
    public StatelessDefaultSubjectFactory subjectFactory(){
        return new StatelessDefaultSubjectFactory();
    }

}