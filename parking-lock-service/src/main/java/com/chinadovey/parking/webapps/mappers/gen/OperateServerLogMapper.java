package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.OperateServerLog;
import com.chinadovey.parking.webapps.pojo.OperateServerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OperateServerLogMapper{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int countByExample(OperateServerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int deleteByExample(OperateServerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int insert(OperateServerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int insertSelective(OperateServerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    List<OperateServerLog> selectByExample(OperateServerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    OperateServerLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int updateByExampleSelective(@Param("record") OperateServerLog record, @Param("example") OperateServerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int updateByExample(@Param("record") OperateServerLog record, @Param("example") OperateServerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int updateByPrimaryKeySelective(OperateServerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    int updateByPrimaryKey(OperateServerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operate_log
     *
     * @mbggenerated Wed Oct 19 10:17:35 CST 2016
     */
    List<OperateServerLog> selectByExample(OperateServerLogExample example, RowBounds rowBounds);
}