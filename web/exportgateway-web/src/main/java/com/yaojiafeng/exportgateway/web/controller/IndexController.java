package com.yaojiafeng.exportgateway.web.controller;

import com.yaojiafeng.exportgateway.web.annotation.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/21 下午5:05 $
 */
@Controller
@Login
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/error"})
    public void error() {
        throw new RuntimeException("error");
    }

}
