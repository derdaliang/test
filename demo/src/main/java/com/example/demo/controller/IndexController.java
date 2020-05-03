package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.IndexUser;
import com.example.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lixuefeng
 * @date 2020/3/17 22:41
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/login")
    public String index()
    {
        return "login";
    }
    @PostMapping("/login")
    public String index(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "password") String password,
            Model model
    )
    {
        model.addAttribute("userId",userId);
        model.addAttribute("password",password);


        IndexUser user=null;
        if (user==null)
        {
            model.addAttribute("error","用户或密码错误");
            return "login";
        }
        return "/";
    }
}
