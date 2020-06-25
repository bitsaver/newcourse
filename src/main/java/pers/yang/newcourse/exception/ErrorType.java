package pers.yang.newcourse.exception;

public enum  ErrorType {

    PASSWORD_INCORRECT(600,"密码错误"),
    NO_LOGIN(601,"需要登录"),
    ALREADY_ENROLLED(602,"已参加课程"),
    ALREADY_QUIT(603, "已退课"),
    NO_ROLE(604,"拥有特定角色才可访问")

    ;




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