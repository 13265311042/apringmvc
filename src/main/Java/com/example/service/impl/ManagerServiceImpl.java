package com.example.service.impl;

import com.example.dao.ManagerMapper;
import com.example.pojo.Manager;
import com.example.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hyt on 2018/3/1.
 * @author hyt
 */
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerMapper managerMapper;


    @Override
    public Manager getNameById(Long aLong) {
        return managerMapper.getNameById(aLong);
    }
}
