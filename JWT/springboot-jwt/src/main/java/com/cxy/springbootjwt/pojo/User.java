package com.cxy.springbootjwt.pojo;

import lombok.Data;

@Data
public class User {
    private String username;
    private String pwd;
    private String token;
}
