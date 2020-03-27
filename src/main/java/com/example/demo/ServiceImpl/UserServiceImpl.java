package com.example.demo.ServiceImpl;

import com.example.demo.Dao.SS;
import com.example.demo.Service.UserService;
import com.example.demo.mapper.SSMapper;
import org.slf4j.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private SSMapper ssMapper;


    @Override
    public SS selectByPrimaryKey(Integer userId) {
        logger.info("进入实现类方法");
        return ssMapper.selectByPrimaryKey(userId);
    }
}
