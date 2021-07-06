package com.cxy.springbootjwt.controller;

import com.cxy.springbootjwt.pojo.User;
import com.cxy.springbootjwt.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private final String  USERNAME="admin";
    private final String PASSWORD="123456";

    @GetMapping("/login")
    public User login(User user){
        System.out.println(user);
        if(USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPwd())){
            //添加token
            user.setToken(JwtUtil.createToken());
            return user;
        }
        return null;
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token=request.getHeader("token");
        return JwtUtil.checkToken(token);
    }
}
