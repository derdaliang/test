package com.example.demo.dto;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @author lixuefeng
 * @date 2020/3/3 21:36
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
