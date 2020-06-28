package pers.yang.newcourse.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.utils.ResponseUtils;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Response handle(CustomException ce){
        return ResponseUtils.error(ce);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(RuntimeException re){
        re.printStackTrace();
        if (re instanceof CustomException) {
            return ResponseUtils.error((CustomException)re);
        }
        if (re instanceof AuthenticationException) {
            return ResponseUtils.error(ErrorType.AUTHENTIC_FIALED);
        }
        return ResponseUtils.error(ErrorType.OPERATE_FAIL);
    }
}
