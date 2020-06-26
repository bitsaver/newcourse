package pers.yang.newcourse.config.jwt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.yang.newcourse.exception.ErrorType;
import pers.yang.newcourse.utils.JWTUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class RoleFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //从请求中获取token参数
        String token = httpServletRequest.getHeader("Token");
        Long id = JWTUtil.getUserId(token);

        if(id == null){
            return false;
        }

        //获取当前路径所需要的角色
        String[] rolesArray = (String[]) o;
        //如果有配置
        if (rolesArray != null && rolesArray.length!=0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            boolean flag = false;
            for (String item : roles) {
                System.out.println(item+"才可以访问======================>"+SecurityUtils.getSubject().getSession().getId()+ SecurityUtils.getSubject().hasRole(item));
                if (getSubject(servletRequest,servletResponse).hasRole(item)) {
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
        response.getWriter().write("{\"code\":" + ErrorType.NO_ROLE.getCode()
                + ",\"msg\":\""+ ErrorType.NO_ROLE.getMsg() +"\"}");
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        System.out.println("preHandle()  "+httpServletRequest.getHeader("Token"));
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin")); //标识允许哪个域到请求，直接修改成请求头的域
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");//标识允许的请求方法
        // 响应首部 Access-Control-Allow-Headers 用于 preflight request （预检请求）中，列出了将会在正式请求的 Access-Control-Expose-Headers 字段中出现的首部信息。修改为请求首部
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            System.out.println("OPTIONS试探");
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }



}
