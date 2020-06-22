package pers.yang.newcourse.utils;

import org.apache.shiro.authc.AuthenticationException;
import pers.yang.newcourse.exception.LoginException;

public class ExceptionUtil {

    public static final AuthenticationException LOGINEXCEPTION = new LoginException("账号或密码错误","403001");

}
