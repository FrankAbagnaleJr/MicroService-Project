package com.kyrie.service;

import com.kyrie.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User selectUserById(@Param("id") Long id);
}
