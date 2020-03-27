package com.example.demo.mapper;

import com.example.demo.Dao.SS;

public interface SSMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SS record);

    int insertSelective(SS record);

    SS selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SS record);

    int updateByPrimaryKey(SS record);
}