package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int countByExample(CompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int deleteByExample(CompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int insert(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int insertSelective(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    List<Company> selectByExample(CompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    Company selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int updateByPrimaryKeySelective(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    int updateByPrimaryKey(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_company
     *
     * @mbggenerated Mon Sep 26 10:35:44 CST 2016
     */
    List<Company> selectByExample(CompanyExample example, RowBounds rowBounds);
}