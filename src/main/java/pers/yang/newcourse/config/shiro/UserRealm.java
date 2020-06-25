package pers.yang.newcourse.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import pers.yang.newcourse.config.jwt.JWTToken;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.service.UserService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 从数据库中查到用户的角色和权限，然后对用户进行授权
     * @param principalCollection
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
/*
        String username = JWTUtil.getUsername(principalCollection.toString());

        System.out.println("-------------------->"+username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<String> nameList = userService.getRoleListByUserName(username);
        // 5. 添加权限
        info.addRoles(nameList);
        //将数据库中的权限信息添加给用户
    //info.addStringPermission("user:add");

        return info;*/
return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        //查询数据库中是否存在用户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",username);
        User user = new User();
        user = user.selectOne(queryWrapper);

        if (user== null){
            throw new AuthenticationException("用户不存在!");
        }

        return new SimpleAuthenticationInfo(token,user.getPassword(),"userRealm");
    }
}