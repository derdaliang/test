package com.example.demo.execption;

/**
 * @author lixuefeng
 * @date 2020/4/5 13:18
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"the question which you find is miss or Non-existent,Please try another or Wait a minute"),
    TARGET_PARAM_NOT_FOUND(2002,"dont choice anyone question or comment"),
    NO_LOGIN(2003,"Not logged in,Please login in"),
    SYS_ERROR(2004,"System error,please wait a moment"),
    TYPE_PARAM_NOT_FOUND(2005,"type is dont exist"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    CONTENT_IS_ENPTY(2007,"评论不可为空");
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code, String message)
    {
        this.message=message;this.code=code;
    }
}
