package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.pojo.DasConnectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DasConnectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int countByExample(DasConnectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int deleteByExample(DasConnectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int deleteByPrimaryKey(String dasId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int insert(DasConnect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int insertSelective(DasConnect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    List<DasConnect> selectByExample(DasConnectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    DasConnect selectByPrimaryKey(String dasId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int updateByExampleSelective(@Param("record") DasConnect record, @Param("example") DasConnectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int updateByExample(@Param("record") DasConnect record, @Param("example") DasConnectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int updateByPrimaryKeySelective(DasConnect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    int updateByPrimaryKey(DasConnect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_das_connect
     *
     * @mbggenerated Mon Jul 17 14:51:12 CST 2017
     */
    List<DasConnect> selectByExample(DasConnectExample example, RowBounds rowBounds);
}