package pers.yang.newcourse.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import pers.yang.newcourse.utils.JWTUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

public class RoleFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //从请求中获取token参数
        String token = httpServletRequest.getHeader("Token");
        String username = JWTUtil.getUsername(token);
        if(username == null){
            return false;
        }

        //获取当前路径所需要的角色
        String[] rolesArray = (String[]) o;
        //如果有配置
        if (rolesArray != null && rolesArray.length!=0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            boolean flag = false;
            for (String item : roles) {

                System.out.println("isAA()===========================>"+SecurityUtils.getSubject().hasRole(item));
                if (SecurityUtils.getSubject().hasRole(item)) {
                    flag = true;
                }
            }
            return flag;
        } else {
            return true;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        //throw new UnknownAccountException();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"code\":400123,\"message\":\"无访问权限\"}");
        return false;
    }


}
