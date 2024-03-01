package com.xiang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiang.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface usermapper extends BaseMapper<user> {
}
