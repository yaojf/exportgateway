package com.yaojiafeng.exportgateway.dal.mapper;

import com.yaojiafeng.exportgateway.dal.entity.App;
import com.yaojiafeng.exportgateway.common.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer id);

    List<App> getAppList(@Param("status") Byte status, @Param("appName") String appName, @Param("page") Page page);

    int getAppListNum(@Param("status") Byte status, @Param("appName") String appName);

    int updateByPrimaryKeySelective(App app);

    List<App> selectAll();

    App findByAppId(String appId);
}