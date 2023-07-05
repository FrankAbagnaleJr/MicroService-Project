package com.kyrie.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;

public interface UserService {
    /**
     *
     * @param page 分页参数
     * @param dto  查询条件
     * @return
     */
    PageResult<User> queryUserList(PageParams page, QueryUserParamsDto dto);

    User selectById(Long id);

}
