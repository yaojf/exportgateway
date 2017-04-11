package com.yaojiafeng.exportgateway.web.session;

import com.yaojiafeng.exportgateway.biz.common.session.UserSession;
import com.yaojiafeng.exportgateway.common.utils.RedisUtils;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午2:44 $
 */
public class UserSessionContainer {

    public static final int expire = 3600;

    public UserSession findUserBySessionId(String sessionId) {
        return (UserSession) RedisUtils.get(sessionId);
    }

    public void setUserBySessionId(String sessionId, UserSession userSession) {
        RedisUtils.set(sessionId, userSession, expire);
    }

    public void removeUserBySessionId(String sessionId) {
        RedisUtils.delete(sessionId);
    }
}
