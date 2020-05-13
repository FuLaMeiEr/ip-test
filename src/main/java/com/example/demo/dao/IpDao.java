package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 13:54 2020/5/13
 **/
@Mapper
public interface IpDao {
    @Select(value = "select ip from iptest")
    List<String> getIpList();
}
