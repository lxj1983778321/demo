package com.example.demo.controller;

import com.example.demo.Dao.USE_INFO;
import com.example.demo.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseController {

    @Autowired
    UseService useService;

    @RequestMapping(value = "/use/query",method = {RequestMethod.POST,RequestMethod.GET})
    public USE_INFO query(@RequestBody Integer id){
        System.out.println("传入的参数为： "+id);
        return useService.query(id);
    }
}
