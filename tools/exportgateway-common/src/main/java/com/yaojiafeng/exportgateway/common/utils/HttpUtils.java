package com.yaojiafeng.exportgateway.common.utils;

import com.yaojiafeng.exportgateway.common.Constants;
import com.yaojiafeng.exportgateway.common.spring.SpringConfig;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yaojiafeng on 16/2/19.
 */
public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager connManager = null;

    private static CloseableHttpClient httpclient = null;

    /**
     * 服务器处理超时时间
     */
    public final static int SOCKET_TIMEOUT = Integer.parseInt(SpringConfig.get("socketTimeout", "3000"));

    /**
     * 连接服务器超时时间
     */
    public final static int CONNECT_TIMEOUT = Integer.parseInt(SpringConfig.get("connectTimeout", "1500"));

    /**
     * 获取manager中连接超时时间
     */
    public final static int CONNECTION_REQUEST_TIMEOUT = Integer.parseInt(SpringConfig.get("connectionRequestTimeout", "500"));

    static {
        try {
            SSLContext sslContext = SSLContexts.custom().useTLS().build();
            sslContext.init(null,
                    new TrustManager[]{new X509TrustManager() {

                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                X509Certificate[] certs, String authType) {
                        }
                    }}, null);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();
            // Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom()
                    .setMaxHeaderCount(Integer.parseInt(SpringConfig.get("maxHeaderCount", "200")))
                    .setMaxLineLength(Integer.parseInt(SpringConfig.get("maxLineLength", "2000")))
                    .build();

            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints)
                    .build();
            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(Integer.parseInt(SpringConfig.get("maxTotal", "300")));
            connManager.setDefaultMaxPerRoute(Integer.parseInt(SpringConfig.get("defaultMaxPerRoute", "300")));
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        }
    }

    /**
     * @param url
     * @param body irstname=abcde&lastname=fghi
     * @return
     */
    public static String post(String url, String body) {
        return post(url, body, Constants.ENCODING, SOCKET_TIMEOUT, CONNECT_TIMEOUT, CONNECTION_REQUEST_TIMEOUT);
    }

    public static String post(String url, String body, String encoding, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        HttpPost post = new HttpPost(url);
        try {
//            post.setHeader("Content-type", "application/json");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(body, encoding);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(stringEntity);

            logger.info("[HttpUtils Post] begin invoke url:" + url + " , params:" + body);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, encoding);
                        logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :" + str);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, Constants.ENCODING, SOCKET_TIMEOUT, CONNECT_TIMEOUT, CONNECTION_REQUEST_TIMEOUT);
    }

    public static String get(String url, Map<String, String> params, String encode,
                             int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).build();

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        int i = 0;
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i == 0 && !url.contains("?")) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                sb.append("=");
                String value = entry.getValue();
                try {
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("encode http get params error, value is " + value, e);
                    sb.append(URLEncoder.encode(value));
                }
                i++;
            }
        }
        logger.info("[HttpUtils Get] begin invoke:" + sb.toString());
        HttpGet get = new HttpGet(sb.toString());
        get.setConfig(requestConfig);

        try {
            CloseableHttpResponse response = httpclient.execute(get);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error(String.format("[HttpUtils Get]get response error, url:%s", sb.toString()), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(String.format("[HttpUtils Get]Debug url:%s , response string %s:", sb.toString(), responseString));
        } catch (SocketTimeoutException e) {
            logger.error(String.format("[HttpUtils Get]invoke get timout error, url:%s", sb.toString()), e);
            return responseString;
        } catch (Exception e) {
            logger.error(String.format("[HttpUtils Get]invoke get error, url:%s", sb.toString()), e);
        } finally {
            get.releaseConnection();
        }
        return responseString;
    }

    public static String postHttp(String reqURL, Map<String, String> params) {
        return postHttp(reqURL, params, Constants.ENCODING, SOCKET_TIMEOUT, CONNECT_TIMEOUT, CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * HTTPS请求
     *
     * @param reqURL
     * @param params
     * @return
     */
    public static String postHttp(String reqURL, Map<String, String> params, String encode, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        String responseContent = null;

        HttpPost httpPost = new HttpPost(reqURL);
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout).build();

            List<NameValuePair> formParams = new ArrayList<NameValuePair>();

            if (params != null) {
                // 绑定到请求 Entry
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, Consts.UTF_8));
            httpPost.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 执行POST请求
                HttpEntity entity = response.getEntity(); // 获取响应实体
                try {
                    if (null != entity) {
                        responseContent = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info("requestURI : " + httpPost.getURI() + ", responseContent: " + responseContent);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            httpPost.releaseConnection();
        }
        return responseContent;
    }

    public static String execute(HttpRequestBase request) {
        return execute(request, Constants.ENCODING, SOCKET_TIMEOUT, CONNECT_TIMEOUT, CONNECTION_REQUEST_TIMEOUT);
    }

    public static String execute(HttpRequestBase request, String encode,
                                 int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        String responseContent = null;

        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout).build();

            request.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(request);
            try {
                HttpEntity entity = response.getEntity(); // 获取响应实体
                try {
                    if (null != entity) {
                        responseContent = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            request.releaseConnection();
        }

        return responseContent;
    }

}
