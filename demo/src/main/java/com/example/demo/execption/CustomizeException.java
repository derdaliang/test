package com.example.demo.execption;

/**
 * @author lixuefeng
 * @date 2020/4/5 12:59
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code=errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
