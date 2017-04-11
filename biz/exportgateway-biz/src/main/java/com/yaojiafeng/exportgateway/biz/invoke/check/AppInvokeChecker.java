package com.yaojiafeng.exportgateway.biz.invoke.check;

import com.google.common.collect.Maps;
import com.yaojiafeng.exportgateway.biz.invoke.exception.InvokeException;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import com.qccr.exportgateway.dal.dao.AppDao;
import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.dal.entity.Constants;
import com.yaojiafeng.exportgateway.facade.ogw.protocol.EGProtocol;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * app合法性校验
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:28 $
 */
@Component
@Order(1)
public class AppInvokeChecker extends AbstractInvokeChecker {
    @Autowired
    private AppDao appDao;

    private volatile Map<String, App> appLookupMap;

    @Override
    public void doCheck(RpcInvokeRequest request, RpcInvokeResponse response) {
        EGProtocol egProtocol = request.getEgProtocol();
        if (StringUtils.isBlank(egProtocol.getAppID())) {
            throw new InvokeException(StateCodes.DOES_NOT_EXIST_APPID);
        }

        App app = appLookupMap.get(egProtocol.getAppID());
        if (app == null) {
            throw new InvokeException(StateCodes.DOES_NOT_EXIST_APPID);
        }

        if (!app.getToken().equals(egProtocol.getToken())) {
            throw new InvokeException(StateCodes.TOKEN_DOES_NOT_MATCH);
        }

        if (!app.getStatus().equals(Constants.VALID_STATUS)) {
            throw new InvokeException(StateCodes.THE_CURRENT_APPID_IS_NOT_AVAILABLE);
        }

        request.setApp(app);
    }

    @Override
    public synchronized void init() {
        Map<String, App> appMap = Maps.newHashMap();
        List<App> appList = appDao.findAll();
        if (CollectionUtils.isNotEmpty(appList)) {
            for (App app : appList) {
                appMap.put(app.getAppId(), app);
            }
        }
        this.appLookupMap = appMap;
    }

}
