package com.example.service;

import com.example.pojo.Manager;

/**
 * Created by hyt on 2018/3/1.
 * @author hyt
 */


public interface ManagerService {
    /**
     * 得到名字
     * @param aLong 传过来的参数
     * @return Result<int>
     */
    Manager getNameById(Long aLong);
}
