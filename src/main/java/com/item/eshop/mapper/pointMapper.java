package com.item.eshop.mapper;

import com.item.eshop.model.point;

public interface pointMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    int insert(point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    int insertSelective(point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    point selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    int updateByPrimaryKeySelective(point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point
     *
     * @mbg.generated Sun Aug 19 14:10:56 CST 2018
     */
    int updateByPrimaryKey(point record);
}