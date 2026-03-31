package com.ryan.aicodegenerator.model.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ryan.aicodegenerator.model.entity.User;
import com.ryan.aicodegenerator.model.mapper.UserMapper;
import com.ryan.aicodegenerator.model.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author Jasonare
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{

}
