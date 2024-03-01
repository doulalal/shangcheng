package com.xiang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiang.pojo.authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface authmapper extends BaseMapper<authority> {
}
