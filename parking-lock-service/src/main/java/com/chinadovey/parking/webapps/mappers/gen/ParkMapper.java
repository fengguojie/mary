package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.ParkExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ParkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int countByExample(ParkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int deleteByExample(ParkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int insert(Park record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int insertSelective(Park record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    List<Park> selectByExample(ParkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    Park selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int updateByExampleSelective(@Param("record") Park record, @Param("example") ParkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int updateByExample(@Param("record") Park record, @Param("example") ParkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int updateByPrimaryKeySelective(Park record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    int updateByPrimaryKey(Park record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_park
     *
     * @mbggenerated Sun Jul 03 16:02:39 CST 2016
     */
    List<Park> selectByExample(ParkExample example, RowBounds rowBounds);
    /**
     * 查询中心经纬度周围的点
     * @param map 
     * @return
     */
    List<Park> selectByCoordinates(Map<String,Double> map, RowBounds rowBounds);
    /**
     * 按价格排序
     * @param map 
     * @return
     */
    List<Park> sortByPrice(ParkExample example, RowBounds rowBounds);
    
    /**
     * 查询中心经纬度周围最近的一家停车场
     * @param mao
     * @return
     */
    Park selectByNeighborhood(Map<String,Double> map);

    
    
    List<Park> spaceOnPark(ParkExample example);
    List<Park> spaceOnPark1(Map<String,Double> map);
    Park selectSumByExample(ParkExample ParkExample);   

}

