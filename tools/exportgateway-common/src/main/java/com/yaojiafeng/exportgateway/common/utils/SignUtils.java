package com.yaojiafeng.exportgateway.common.utils;

import com.google.common.collect.Maps;
import com.yaojiafeng.exportgateway.common.Configs;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/12 下午5:29 $
 */
public class SignUtils {

    public static final String SIGN = "sign";

    private static String getContent(Map<String, String> params) {
        if (params == null) {
            return null;
        } else {
            params.remove(SIGN);
            List<String> keys = new ArrayList<String>(params.keySet());
            Collections.sort(keys);
            StringBuilder sb = new StringBuilder();
            for (String currKey : keys) {
                sb.append(currKey).append(params.get(currKey));
            }
            return sb.toString();
        }
    }


    public static String md5SignV1(Map<String, String> params, String secretKey) {
        return EncryptUtils.MD5(getContent(params) + secretKey);
    }

    public static boolean md5CheckV1(Map<String, String> params, String secretKey) {
        String sign = params.get(SIGN);
        String checkSign = EncryptUtils.MD5(getContent(params) + secretKey);
        return sign.equals(checkSign);
    }

    public static boolean md5CheckV2(String body, String secretKey, String checkSign) {
        String sign = EncryptUtils.MD5(body + secretKey);
        return sign.equals(checkSign);
    }


    public static String rsaSignV1(Map<String, String> params, String privateKey, String charset) {
        String signContent = getSignContent(params);
        return rsaSign(signContent, privateKey, charset);
    }

    public static boolean rsaCheckV1(Map<String, String> params, String publicKey, String charset) {
        String sign = params.get(SIGN);
        String content = getSignContent(params);
        return rsaCheckContent(content, sign, publicKey, charset);
    }

    public static boolean rsaCheckV2(String body, String publicKey, String checkSign, String charset) {
        return rsaCheckContent(body, checkSign, publicKey, charset);
    }

    private static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) {
        try {
            PublicKey e = getPublicKeyFromX509("RSA", publicKey.getBytes());
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(e);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception ex) {
            throw new RuntimeException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, ex);
        }
    }

    private static PublicKey getPublicKeyFromX509(String algorithm, byte[] encodedKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    private static String getSignContent(Map<String, String> params) {
        if (params == null) {
            return null;
        } else {
            params.remove(SIGN);
            StringBuffer content = new StringBuffer();
            List<String> keys = new ArrayList(params.keySet());
            Collections.sort(keys);

            for (int i = 0; i < keys.size(); ++i) {
                String key = keys.get(i);
                String value = params.get(key);
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            }

            return content.toString();
        }
    }


    private static String rsaSign(String content, String privateKey, String charset) {
        try {
            PrivateKey e = getPrivateKeyFromPKCS8("RSA", privateKey.getBytes());
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(e);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (InvalidKeySpecException ikse) {
            throw new RuntimeException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ikse);
        } catch (Exception ex) {
            throw new RuntimeException("RSAcontent = " + content + "; charset = " + charset, ex);
        }
    }

    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, byte[] encodedKey) throws Exception {
        if (encodedKey != null && StringUtils.isNotBlank(algorithm)) {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            encodedKey = Base64.decodeBase64(encodedKey);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALlx6R86h9D/MDZJ2gidQIzo6dGf" +
                "NBj79w3B677NDF07xGlWqi1XbNNXKXnCT7EQcfLDpE4M4lMSEHpsQaKJIbvEWEmZrRWJYxxXpf+2" +
                "kGqEkLgksm+HhTUQsiKbhUfok7Sz2Z4PwRetqg7+Mq1vtIh2qB7HpyyCJAP7OhrSbFr/AgMBAAEC" +
                "gYBFoOaWCiT6rQZOK/aIq3qULdO1GGC4TMrbSIeakWTmEM3Te23ULnI2/BAfnnzLj9ApBZpIj//e" +
                "jvbO8JzISVL3htZmPyPvWqlF6tbb1W/vCRFJsOCqtWFDXaZ0TUcKUGgK6S1lmXUpXdIeg/NhC6m2" +
                "QURkQarpILIvl0/uhaLKmQJBAP4k9eAdqDKcp1qQfskwyFCz9ROiBrtpHD0cPQQtWn2HJvWaZvL3" +
                "L7hQvZfjtZz1IFhRZndifUZyUMAapjNypaMCQQC6zIoGlhHgDcm+v5A3pZpZD+MzlE9O4fGdz720" +
                "8CAG2UrInbaGswJ81EYhwrfJ+uJ24xGYnOCiH8mVPksa3jL1AkEAk42d+4r8drYhLCGsmt7pzoFN" +
                "DyMJSZITRR+6BUzXtv3bDsmgb/w4BolKKn+YmBJxvxUzqqjSFy6T15UhXcl4hQJAUYvtTYbB8u/R" +
                "/MWf7csLoInBA8lg/ezBD8Wa6ZxSPuspLH89KXyIP3pjy2U32qL9rizeh6MkpWH/HHn/S5Fo6QJB" +
                "AOrjAHc5UkRfgFyRabP07MwBBzIw+RtoTb/Pcui3vtTQRBHz6Sh1mZ2i5ti2vOAlfK6dfmnLKf8+" +
                "ixtLbwH5yrA=";

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5cekfOofQ/zA2SdoInUCM6OnRnzQY+/cNweu+" +
                "zQxdO8RpVqotV2zTVyl5wk+xEHHyw6RODOJTEhB6bEGiiSG7xFhJma0ViWMcV6X/tpBqhJC4JLJv" +
                "h4U1ELIim4VH6JO0s9meD8EXraoO/jKtb7SIdqgex6csgiQD+zoa0mxa/wIDAQAB";

        Map<String, String> map = Maps.newHashMap();
        map.put("a", "1");
        map.put("b", "2");

        String s = rsaSignV1(map, privateKey, Configs.CHARSET);
        map.put("sign", s);

        boolean b = rsaCheckV1(map, publicKey, Configs.CHARSET);
        System.out.println(b);



//        String s1 = md5SignV1(map, Configs.QCCR_MD5_SECRET_KEY);
//        map.put("sign", s1);
//
//        System.out.println(md5CheckV1(map, Configs.QCCR_MD5_SECRET_KEY));
    }


}
