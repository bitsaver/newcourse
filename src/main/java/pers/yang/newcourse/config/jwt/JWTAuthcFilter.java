package pers.yang.newcourse.config.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
@Slf4j
public class JWTAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String token = ((HttpServletRequest) request).getHeader("Token");

        if (token!=null) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                JWTToken jwtToken = new JWTToken(token);
                getSubject(request, response).login(jwtToken);
            } catch (Exception e) {
                if (e instanceof CustomException) {
                    throw (CustomException)e;
                }
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //throw new UnknownAccountException();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"code\":"+ ErrorType.NO_LOGIN.getCode() +",\"msg\":"+ErrorType.NO_LOGIN.getMsg()+"}");
        return false;
    }
}
