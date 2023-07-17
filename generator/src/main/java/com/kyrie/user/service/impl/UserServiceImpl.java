package com.kyrie.user.service.impl;

import com.kyrie.user.pojo.User;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jijinliang
 * @since 2023-07-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
