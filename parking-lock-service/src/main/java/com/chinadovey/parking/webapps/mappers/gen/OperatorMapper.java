package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.Operator;
import com.chinadovey.parking.webapps.pojo.OperatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OperatorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int countByExample(OperatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int deleteByExample(OperatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int insert(Operator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int insertSelective(Operator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    List<Operator> selectByExample(OperatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    Operator selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int updateByExampleSelective(@Param("record") Operator record, @Param("example") OperatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int updateByExample(@Param("record") Operator record, @Param("example") OperatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int updateByPrimaryKeySelective(Operator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    int updateByPrimaryKey(Operator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_operator
     *
     * @mbggenerated Tue Jan 26 11:13:05 CST 2016
     */
    List<Operator> selectByExample(OperatorExample example, RowBounds rowBounds);
}