/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.controller;

import com.qccr.exportgateway.biz.service.AppService;
import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.web.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午10:56 $
 */
@Controller
@RequestMapping(value = "/app")
@Login
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/list")
    public String appList(@RequestParam(value = "status", defaultValue = "1") Byte status,
                          @RequestParam(value = "appName", defaultValue = "") String appName,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          ModelMap modelMap) {
        Page page = new Page(pageNum, pageSize);

        List<App> appList = appService.findAppList(status, appName, page);
        modelMap.put("appList", appList);

        modelMap.put("page", page);
        modelMap.put("status", status);
        modelMap.put("appName", appName);

        return "app/app_list";
    }


    @RequestMapping(value = "/add")
    public String appAdd() {
        return "app/app_add";
    }

    @RequestMapping(value = "/save")
    public String appSave(@RequestParam(value = "id") Integer id,
                          @RequestParam(value = "app_id") String appId,
                          @RequestParam(value = "app_name") String appName,
                          @RequestParam(value = "token") String token,
                          @RequestParam(value = "status") Byte status,
                          ModelMap modelMap) {

        App app = new App();
        app.setId(id);
        app.setAppId(appId);
        app.setAppName(appName);
        app.setToken(token);
        app.setStatus(status);
        try {
            appService.saveApp(app);
        } catch (Exception ex) {
            if (id != null) {
                App app1 = appService.findById(id);
                modelMap.put("app", app1);
            }
            modelMap.put("errorMsg", ex.getMessage());

            return id == null ? "app/app_add" : "app/app_update";
        }

        return "redirect:/app/list";
    }

    @RequestMapping(value = "/update")
    public String appUpdate(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        App app = appService.findById(id);

        modelMap.put("app", app);

        return "app/app_update";
    }


    @RequestMapping(value = "/delete")
    public String appDelete(@RequestParam(value = "id") Integer id) {
        appService.deleteById(id);

        return "redirect:/app/list";
    }
}
