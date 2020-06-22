package pers.yang.newcourse.exception;

import org.apache.shiro.authc.AuthenticationException;

public class LoginException extends AuthenticationException {

    public LoginException(String message,String errorcode) {
        super(message);
    }
}
