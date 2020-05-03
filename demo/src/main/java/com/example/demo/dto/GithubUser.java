package com.example.demo.dto;

import com.example.demo.provider.GithubProvider;
import lombok.Data;

/**
 * @author lixuefeng
 * @date 2020/3/4 19:59
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
