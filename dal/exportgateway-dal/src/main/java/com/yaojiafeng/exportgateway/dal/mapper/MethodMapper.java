package com.yaojiafeng.exportgateway.dal.mapper;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.Method;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MethodMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Method record);

    Method selectByPrimaryKey(Integer id);

    List<Method> selectAll();

    int updateByPrimaryKeySelective(Method method);

    int getMethodListNum(@Param("status") Byte status, @Param("name") String name, @Param("systemId") Integer systemId);

    List<Method> getMethodList(@Param("status") Byte status, @Param("name") String name, @Param("systemId") Integer systemId, @Param("page") Page page);
}