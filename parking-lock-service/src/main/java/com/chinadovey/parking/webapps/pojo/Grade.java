package com.chinadovey.parking.webapps.pojo;

public class Grade {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade.id
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade.grade_type
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    private String gradeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade.grade
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    private Integer grade;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade.id
     *
     * @return the value of grade.id
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade.id
     *
     * @param id the value for grade.id
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade.grade_type
     *
     * @return the value of grade.grade_type
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade.grade_type
     *
     * @param gradeType the value for grade.grade_type
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade.grade
     *
     * @return the value of grade.grade
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade.grade
     *
     * @param grade the value for grade.grade
     *
     * @mbggenerated Fri Aug 11 21:06:05 CST 2017
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}