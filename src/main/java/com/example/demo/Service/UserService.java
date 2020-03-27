package com.example.demo.Service;

import com.example.demo.Dao.SS;

import javax.annotation.Resource;

@Resource
public interface UserService {
    SS selectByPrimaryKey(Integer userId);
}
