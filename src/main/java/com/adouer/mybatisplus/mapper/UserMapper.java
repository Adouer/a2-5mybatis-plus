package com.adouer.mybatisplus.mapper;

import com.adouer.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 继承BaseMapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
