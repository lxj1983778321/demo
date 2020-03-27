package com.example.demo.controller;


import com.example.demo.Dao.SS;
import com.example.demo.Service.UserService;
import com.example.demo.common.MsgResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/hello")
    public MsgResponse hello(@RequestBody SS s) {
        SS ss = userService.selectByPrimaryKey(s.getUserId());
        return new MsgResponse(0,"success",ss,0);
    }
}
