package com.qccr.exportgateway.dal.mapper;

import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.AppMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppMethodMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(AppMethod record);

    AppMethod selectByPrimaryKey(Integer id);

    List<AppMethod> selectAll();

    int getAppMethodListNum(@Param("status") Byte status, @Param("appId") Integer appId);

    List<AppMethod> getAppMethodList(@Param("status") Byte status, @Param("appId") Integer appId, @Param("page") Page page);

    int updateByPrimaryKeySelective(AppMethod appMethod);

    AppMethod findByAppIdAndMethodId(@Param("appId") Integer appId, @Param("methodId") Integer methodId);
}