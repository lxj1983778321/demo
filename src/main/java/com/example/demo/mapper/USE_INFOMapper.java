package com.example.demo.mapper;

import com.example.demo.Dao.USE_INFO;

public interface USE_INFOMapper {
    int deleteByPrimaryKey(Integer useId);

    int insert(USE_INFO record);

    int insertSelective(USE_INFO record);

    USE_INFO selectByPrimaryKey(Integer useId);

    int updateByPrimaryKeySelective(USE_INFO record);

    int updateByPrimaryKey(USE_INFO record);
}