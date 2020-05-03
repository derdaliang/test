package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lixuefeng
 * @date 2020/5/2 16:21
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}

