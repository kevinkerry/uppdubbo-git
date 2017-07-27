package com.csii.upp.qrcodeplatform.generate.dao;

import com.csii.upp.qrcodeplatform.generate.dao.model.Marketingactivitiesrule;
import com.csii.upp.qrcodeplatform.generate.dao.model.MarketingactivitiesruleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketingactivitiesruleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int countByExample(MarketingactivitiesruleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int deleteByExample(MarketingactivitiesruleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int deleteByPrimaryKey(String marketactiveid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int insert(Marketingactivitiesrule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int insertSelective(Marketingactivitiesrule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    List<Marketingactivitiesrule> selectByExample(MarketingactivitiesruleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    Marketingactivitiesrule selectByPrimaryKey(String marketactiveid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int updateByExampleSelective(@Param("record") Marketingactivitiesrule record, @Param("example") MarketingactivitiesruleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int updateByExample(@Param("record") Marketingactivitiesrule record, @Param("example") MarketingactivitiesruleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int updateByPrimaryKeySelective(Marketingactivitiesrule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table marketingactivitiesrule
     *
     * @mbggenerated Tue Jun 06 11:01:03 CST 2017
     */
    int updateByPrimaryKey(Marketingactivitiesrule record);
}