package com.yaojiafeng.exportgateway.biz.invoke.handler.common;

import com.alibaba.fastjson.JSONObject;
import com.yaojiafeng.exportgateway.biz.invoke.exception.InvokeException;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import com.yaojiafeng.exportgateway.common.Configs;
import com.yaojiafeng.exportgateway.common.utils.HttpUtils;
import com.yaojiafeng.exportgateway.common.utils.SignUtils;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import com.qccr.exportgateway.dal.entity.Method;
import com.yaojiafeng.exportgateway.facade.ogw.protocol.EGProtocol;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 上午11:38 $
 */
@Component
public class CommonInvokeHandler implements InvokeHandler {


    @Override
    public String doInvoke(RpcInvokeRequest request, RpcInvokeResponse response) throws Exception {
        Method method = request.getMethod();
        String url = method.getUrl();
        EGProtocol egProtocol = request.getEgProtocol();
        String body = egProtocol.getBody();
        ExternalSystem externalSystem = request.getExternalSystem();

        Map<String, String> queryParam = JSONObject.parseObject(body, Map.class);

        if (method.getSignType().equals(MD5)) {
            return doInvokeMD5(url, queryParam, externalSystem.getMd5SecretKey(), externalSystem.getSignBack());

        } else if (method.getSignType().equals(RSA)) {
            return doInvokeRSA(url, queryParam, externalSystem.getRsaPublicKey(), externalSystem.getSignBack());
        }

        return null;
    }

    /**
     * http调用返回格式
     * <p>
     * {
     * body:"",
     * sign:"":
     * }
     *
     * @param url
     * @param queryParam
     * @param rsaPublicKey
     * @param signBack     是否需要验证回签
     * @return
     * @throws Exception
     */
    protected String doInvokeRSA(String url, Map<String, String> queryParam, String rsaPublicKey, Boolean signBack) throws Exception {
        String sign = SignUtils.rsaSignV1(queryParam, Configs.QCCR_RSA_PRIVATE_KEY, Configs.CHARSET);
        queryParam.put(SIGN, sign);

        String result = HttpUtils.postHttp(url, queryParam);

        JSONObject jsonObject = JSONObject.parseObject(result);

        String returnBody = jsonObject.getString(BODY);

        if (signBack) {
            String returnSign = jsonObject.getString(SIGN);
            if (!SignUtils.rsaCheckV2(returnBody, rsaPublicKey, returnSign, Configs.CHARSET)) {
                throw new InvokeException(StateCodes.VERIFY_SIGNATURE_FAILED);
            }
        }

        return returnBody;
    }

    /**
     * http调用返回格式
     * <p>
     * {
     * body:"",
     * sign:"":
     * }
     *
     * @param url
     * @param queryParam
     * @param md5SecretKey
     * @param signBack     是否需要验证回签
     * @return
     * @throws Exception
     */
    protected String doInvokeMD5(String url, Map<String, String> queryParam, String md5SecretKey, Boolean signBack) throws Exception {
        String sign = SignUtils.md5SignV1(queryParam, Configs.QCCR_MD5_SECRET_KEY);
        queryParam.put(SIGN, sign);

        String result = HttpUtils.postHttp(url, queryParam);

        JSONObject jsonObject = JSONObject.parseObject(result);

        String returnBody = jsonObject.getString(BODY);

        if (signBack) {
            String returnSign = jsonObject.getString(SIGN);
            if (!SignUtils.md5CheckV2(returnBody, md5SecretKey, returnSign)) {
                throw new InvokeException(StateCodes.VERIFY_SIGNATURE_FAILED);
            }
        }

        return returnBody;
    }


}
