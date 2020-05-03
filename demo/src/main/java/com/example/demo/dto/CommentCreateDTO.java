package com.example.demo.dto;

import lombok.Data;

/**
 * @author lixuefeng
 * @date 2020/4/5 19:44
 */
@Data
public class CommentCreateDTO {
    private  Long parentId;
    private String content;
    private Integer type;
}
