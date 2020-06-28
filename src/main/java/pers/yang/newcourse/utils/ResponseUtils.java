package pers.yang.newcourse.utils;

import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;


public class ResponseUtils {

    private static final String SUCCESS = "操作成功！";

    public static Response success(Object obj) {
        Response res = new Response();
        res.setCode(200);
        res.setData(obj);
        res.setMsg(SUCCESS);
        return res;
    }

    public static Response success() {
        return success(null);
    }


    public static Response error(ErrorType errorType) {
        Response res = new Response();
        res.setCode(errorType.getCode());
        res.setMsg(errorType.getMsg());
        return res;
    }

    public static Response error(CustomException ce) {
        Response res = new Response();
        res.setCode(ce.getCode());
        res.setMsg(ce.getMessage());
        return res;
    }



}