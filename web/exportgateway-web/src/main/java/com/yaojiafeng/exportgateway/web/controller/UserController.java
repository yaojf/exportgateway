package com.yaojiafeng.exportgateway.web.controller;

import com.yaojiafeng.exportgateway.biz.common.session.UserSession;
import com.yaojiafeng.exportgateway.web.session.UserSessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午3:18 $
 */
@Controller
@RequestMapping("/user")
public class UserController extends RootController {

    private static final String LOGIN_PAGE = "user/login";

    @Autowired
    private UserSessionContainer userSessionContainer;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLoginGet(HttpServletRequest request, ModelMap modelMap,
                               @RequestParam(value = "service", required = false) String service) {
        UserSession userSession = getUserSession(request);
        if (userSession != null) {
            return redirect(HOME, service);
        }
        modelMap.put("service", service);

        return LOGIN_PAGE;
    }


    @Value("${user.name:admin}")
    private String userName;

    @Value("${user.password:admin}")
    private String userPassword;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLoginPost(HttpServletRequest request,
                                @RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                @RequestParam(value = "service", required = false) String service, ModelMap modelMap) {

        if (userName.equals(this.userName) && password.equals(this.userPassword)) {
            String sessionId = getUserSessionId(request);
            UserSession userSession = new UserSession();
            userSessionContainer.setUserBySessionId(sessionId, userSession);
            return redirect(HOME, service);
        } else {
            modelMap.put("errorMsg", "用户名或者密码不正确");
        }

        modelMap.put("userName", userName);
        modelMap.put("password", password);
        modelMap.put("service", service);

        return LOGIN_PAGE;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String userLogout(HttpServletRequest request) {
        String sessionId = getUserSessionId(request);
        userSessionContainer.removeUserBySessionId(sessionId);

        return redirect(HOME, null);
    }


}
