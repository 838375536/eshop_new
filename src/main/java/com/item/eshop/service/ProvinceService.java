package com.item.eshop.service;

import com.item.eshop.model.Province;
import com.item.eshop.model.School;

import java.util.List;

public interface ProvinceService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    int insert(Province record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    int insertSelective(Province record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    Province selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Province record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Province record);

    /**
     *
     * @return 返回所有省份
     */
    List<Province> selectAllProvince();


    /**
     * add chen 2018-8-9
     * @param  省份
     * @return update data
     */
    // add: chan  2018/4/17
    List<Province> selectMore();

    // add: chan  2018/4/16
    List<Province> selectMoreBySort();

    int updateSortUp(int id,int min,int max);

    int updateSortDown(int id,int min,int max);
}
