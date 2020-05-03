package com.example.demo.dto;

import com.example.demo.execption.CustomizeErrorCode;
import com.example.demo.execption.CustomizeException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author lixuefeng
 * @date 2020/4/5 21:09
 */
@Data
public class ResultDTO<T> {
    private  Integer code;
    private String message;
    private T data;
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return  errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }
    public static ResultDTO okOf()
    {
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("success");
        return resultDTO;
    }
    public static<T> ResultDTO okOf(T t)
    {
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("success");
        resultDTO.setData(t);
        return resultDTO;
    }

}
