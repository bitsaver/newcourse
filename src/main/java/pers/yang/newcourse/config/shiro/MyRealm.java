package pers.yang.newcourse.config.shiro;


import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import pers.yang.newcourse.config.jwt.JWTToken;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;
import pers.yang.newcourse.service.UserService;
import pers.yang.newcourse.utils.JWTUtil;

import java.util.List;

public class MyRealm extends AuthorizingRealm {



    @Autowired
    private UserService userService;


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行 doGetAuthorizationInfo()方法" + principals.getPrimaryPrincipal());
        Long id = (Long) principals.getPrimaryPrincipal();
        List<String> roleList = userService.getRoleListByUserId(id);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("授予权限" + roleList.toString());
        simpleAuthorizationInfo.addRoles(roleList);

//        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws CustomException{
        String token = authenticationToken.getPrincipal().toString();

        Long userId = JWTUtil.getUserId(token);
        if(userId==null){
            throw new CustomException(ErrorType.INVALID_REQUEST);
        }

        User user = new User();
        user.setId(userId);
        user = user.selectById();
        if(user==null){
            throw new CustomException((ErrorType.ID_INCORRECT));
        }

        DecodedJWT verify = JWTUtil.verify(token, user.getId(), user.getPassword());
        if (verify==null) {
            throw new CustomException(ErrorType.PASSWORD_INCORRECT);
        }

        return new SimpleAuthenticationInfo(user.getId(), token, "my_realm");
    }
}