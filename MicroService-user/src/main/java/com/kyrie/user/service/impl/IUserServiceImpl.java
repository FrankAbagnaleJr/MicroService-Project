package com.kyrie.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    private final String RED_USER = "user:";

    @Override
    public IPage queryUserList(PageParams pageParams,@RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        Page page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper();
        return userMapper.selectPage(page, lqw);
    }

    @Override
    public User queryById(Long id) {
        User user = null;

        //先从redis中查，查到了直接返回
        user = (User) redisTemplate.opsForValue().get(RED_USER + id);
        if (user!=null) return user;

        //redis中没有从数据库查，查到了回写redis，并返回
        user = userMapper.selectById(id);
        if (user!=null) {
//            synchronized (this) {
//
//                user = (User) redisTemplate.opsForValue().get(RED_USER + id);
//                if (user != null) return user;

                redisTemplate.opsForValue().set(RED_USER+id,user,10, TimeUnit.SECONDS);
//            }
            return user;
        }

        //redis跟数据库都没，回写redis个空值
        redisTemplate.opsForValue().set(RED_USER,null,10,TimeUnit.SECONDS);
        return user;
    }
}
