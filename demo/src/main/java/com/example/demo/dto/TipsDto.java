package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

/**
 * @author lixuefeng
 * @date 2020/4/18 23:29
 */
@Data
public class TipsDto {
    private Long id;
    private int status;
    private String userName;
    private String questionTitle;
    private Long userid;
    private Long questionid;
    private int type;
}
