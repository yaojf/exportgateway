package com.yaojiafeng.exportgateway.biz.service;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.App;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午11:13 $
 */
public interface AppService {

    List<App> findAppList(Byte status, String appName, Page page);

    int saveApp(App app);

    App findById(Integer id);

    int deleteById(Integer id);

    List<App> findAll();
}
