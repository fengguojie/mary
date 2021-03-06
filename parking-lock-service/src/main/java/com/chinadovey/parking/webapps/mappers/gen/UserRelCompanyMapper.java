package com.chinadovey.parking.webapps.mappers.gen;

import com.chinadovey.parking.webapps.pojo.UserRelCompanyExample;
import com.chinadovey.parking.webapps.pojo.UserRelCompany;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserRelCompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int countByExample(UserRelCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int deleteByExample(UserRelCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int deleteByPrimaryKey(UserRelCompany key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int insert(UserRelCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int insertSelective(UserRelCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    List<UserRelCompany> selectByExample(UserRelCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int updateByExampleSelective(@Param("record") UserRelCompany record, @Param("example") UserRelCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    int updateByExample(@Param("record") UserRelCompany record, @Param("example") UserRelCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_user_rel_company
     *
     * @mbggenerated Tue Apr 19 13:51:03 CST 2016
     */
    List<UserRelCompany> selectByExample(UserRelCompanyExample example, RowBounds rowBounds);
}