package com.chinadovey.parking.webapps.mappers.gen;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.chinadovey.parking.webapps.pojo.UserRelRoleExample;
import com.chinadovey.parking.webapps.pojo.UserRelRole;

public interface UserRelRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int countByExample(UserRelRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int deleteByExample(UserRelRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int deleteByPrimaryKey(UserRelRole key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int insert(UserRelRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int insertSelective(UserRelRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    List<UserRelRole> selectByExample(UserRelRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserRelRole record, @Param("example") UserRelRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    int updateByExample(@Param("record") UserRelRole record, @Param("example") UserRelRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table municipal_user_rel_role
     *
     * @mbggenerated Sun Feb 15 10:41:48 CST 2015
     */
    List<UserRelRole> selectByExample(UserRelRoleExample example, RowBounds rowBounds);
}