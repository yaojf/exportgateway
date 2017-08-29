package com.yaojiafeng.exportgateway.biz.service;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.AppMethod;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:33 $
 */
public interface AppMethodService {

    List<AppMethod> findAppMethodList(Byte status, Integer appId, Page page);

    int saveAppMethod(AppMethod appMethod);

    AppMethod findById(Integer id);

    int deleteById(Integer id);
}
