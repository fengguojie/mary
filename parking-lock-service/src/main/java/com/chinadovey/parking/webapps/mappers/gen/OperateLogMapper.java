package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.OperateLog;
import com.chinadovey.parking.webapps.pojo.OperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OperateLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int countByExample(OperateLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int deleteByExample(OperateLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int insert(OperateLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int insertSelective(OperateLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    List<OperateLog> selectByExample(OperateLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    OperateLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int updateByExampleSelective(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int updateByExample(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int updateByPrimaryKeySelective(OperateLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    int updateByPrimaryKey(OperateLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operate_log
     *
     * @mbggenerated Sun Sep 10 21:06:55 CST 2017
     */
    List<OperateLog> selectByExample(OperateLogExample example, RowBounds rowBounds);
}