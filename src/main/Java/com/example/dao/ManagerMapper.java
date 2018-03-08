package com.example.dao;


import com.example.pojo.Manager;
import org.springframework.stereotype.Component;
/***
 * @author hyt
 */
@Component("ManagerMapper")
public interface ManagerMapper {
    /**
     * fetch data by id
     * @param id 传过来的参数
     * @return Result<int>
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     *@param  record
     * @return  Result<int>
     * */
    int insert(Manager record);

    /**
     * 插入数据
     *@param  record
     * @return  Result<int>
     * */
    int insertSelective(Manager record);

    /**
     * fetch data by id
     * @param id 传过来的参数
     * @return Result<int>
     */
    Manager selectByPrimaryKey(Long id);

    /**
     * update data by id
     * @param record 传过来的参数
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * update data by id
     * @param record 传过来的参数
     * @return Result<int>
     */
    int updateByPrimaryKey(Manager record);

    /**
     * 得到名字
     * @param aLong 传过来的参数
     * @return Result<int>
     */
    Manager getNameById(Long aLong);
}