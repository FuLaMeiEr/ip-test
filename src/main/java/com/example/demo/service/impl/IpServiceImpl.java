package com.example.demo.service.impl;

import com.example.demo.dao.IpDao;
import com.example.demo.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 14:07 2020/5/13
 **/
@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private IpDao ipDao;

    public List<String> getIpList() {

        List<String> ipList = ipDao.getIpList();

        return ipList;
    }


}
