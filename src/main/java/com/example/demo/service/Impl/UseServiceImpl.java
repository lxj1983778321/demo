package com.example.demo.service.Impl;

import com.example.demo.Dao.USE_INFO;
import com.example.demo.mapper.USE_INFOMapper;
import com.example.demo.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("useService")
public class UseServiceImpl implements UseService {

    @Autowired
    USE_INFOMapper use_infoMapper;

    @Override
    public USE_INFO query(Integer id) {
        System.out.println("传入的参数为： "+id);
        return use_infoMapper.selectByPrimaryKey(id);
    }
}
