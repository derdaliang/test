package com.example.demo.dto;

import lombok.Data;

/**
 * @author lixuefeng
 * @date 2020/5/2 15:26
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
