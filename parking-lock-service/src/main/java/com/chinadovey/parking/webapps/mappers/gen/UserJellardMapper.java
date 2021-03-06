package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.UserJellard;
import com.chinadovey.parking.webapps.pojo.UserJellardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserJellardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int countByExample(UserJellardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int deleteByExample(UserJellardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int insert(UserJellard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int insertSelective(UserJellard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    List<UserJellard> selectByExample(UserJellardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    UserJellard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int updateByExampleSelective(@Param("record") UserJellard record, @Param("example") UserJellardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int updateByExample(@Param("record") UserJellard record, @Param("example") UserJellardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int updateByPrimaryKeySelective(UserJellard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    int updateByPrimaryKey(UserJellard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Oct 16 20:58:16 CST 2017
     */
    List<UserJellard> selectByExample(UserJellardExample example, RowBounds rowBounds);
}