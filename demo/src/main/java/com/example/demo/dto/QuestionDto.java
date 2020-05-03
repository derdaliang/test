package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

/**
 * @author lixuefeng
 * @date 2020/3/10 21:58
 */
@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String description;
    private  String tag;
    private Long creator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
