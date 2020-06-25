package pers.yang.newcourse.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Response handle(CustomException ce){
        return ResponseUtils.error(ce.getCode(),ce.getMessage());
    }

    @ExceptionHandler(value =RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();

        return ResponseUtils.error(500,"操作失败！");
    }
}
