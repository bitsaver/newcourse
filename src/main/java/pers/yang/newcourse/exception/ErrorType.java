package pers.yang.newcourse.exception;

public enum  ErrorType {

    OPERATE_FAIL(500,"操作失败！"),

    PASSWORD_INCORRECT(600,"密码错误！"),

    NO_LOGIN(601,"需要登录！"),

    ALREADY_ENROLLED(602,"已参加课程！"),

    ALREADY_QUIT(603, "已退课！"),

    NO_ROLE(604,"用户特定角色才可访问！"),

    ALREADY_ADD_QUIZ(605, "已经添加到试卷中！"),

    ALREADY_REMOVE_FROM_QUIZ(606, "已从试卷中移除！"),

    ID_INCORRECT(607, "账号错误！"),

    INVALID_REQUEST(608, "无效的请求！" ),

    AUTHENTIC_FIALED(609, "身份认证失败！");

    private int code;

    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}