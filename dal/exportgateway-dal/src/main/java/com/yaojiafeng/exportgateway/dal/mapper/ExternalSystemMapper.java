package com.yaojiafeng.exportgateway.dal.mapper;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.ExternalSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExternalSystemMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ExternalSystem record);

    ExternalSystem selectByPrimaryKey(Integer id);

    List<ExternalSystem> selectAll();

    int getExternalSystemListNum(@Param("status") Byte status, @Param("enName") String enName);

    List<ExternalSystem> getExternalSystemList(@Param("status") Byte status, @Param("enName") String enName, @Param("page") Page page);

    int updateByPrimaryKeySelective(ExternalSystem externalSystem);

    ExternalSystem findByEnName(String enName);
}