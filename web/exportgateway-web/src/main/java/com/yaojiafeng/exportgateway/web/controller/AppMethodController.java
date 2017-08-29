package com.yaojiafeng.exportgateway.web.controller;

import com.yaojiafeng.exportgateway.biz.service.AppMethodService;
import com.yaojiafeng.exportgateway.biz.service.AppService;
import com.yaojiafeng.exportgateway.biz.service.MethodService;
import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.App;
import com.yaojiafeng.exportgateway.dal.entity.AppMethod;
import com.yaojiafeng.exportgateway.dal.entity.Method;
import com.yaojiafeng.exportgateway.web.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:41 $
 */
@Controller
@RequestMapping("/app-method")
@Login
public class AppMethodController {

    @Autowired
    private AppMethodService appMethodService;

    @Autowired
    private AppService appService;

    @Autowired
    private MethodService methodService;


    @RequestMapping(value = "/list")
    public String appMethodList(@RequestParam(value = "status", defaultValue = "1") Byte status,
                                @RequestParam(value = "appId", required = false) Integer appId,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                ModelMap modelMap) {
        Page page = new Page(pageNum, pageSize);

        List<AppMethod> appMethodList = appMethodService.findAppMethodList(status, appId, page);
        modelMap.put("appMethodList", appMethodList);

        modelMap.put("page", page);
        modelMap.put("status", status);
        modelMap.put("appId", appId);

        //查询出所有app
        List<App> appList = appService.findAll();
        modelMap.put("appList", appList);

        return "appMethod/app_method_list";
    }


    @RequestMapping(value = "/add")
    public String appMethodAdd(ModelMap modelMap) {

        List<App> appList = appService.findAll();
        modelMap.put("appList", appList);

        List<Method> methodList = methodService.findAll();
        modelMap.put("methodList", methodList);

        return "appMethod/app_method_add";
    }

    @RequestMapping(value = "/save")
    public String appMethodSave(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "app_id") Integer appId,
                                @RequestParam(value = "method_id") Integer methodId,
                                @RequestParam(value = "status") Byte status, ModelMap modelMap) {

        AppMethod appMethod = new AppMethod();
        appMethod.setId(id);
        appMethod.setAppId(appId);
        appMethod.setMethodId(methodId);
        appMethod.setStatus(status);
        try {
            appMethodService.saveAppMethod(appMethod);
        } catch (Exception ex) {
            if (id != null) {
                AppMethod appMethod1 = appMethodService.findById(id);
                modelMap.put("appMethod", appMethod1);
            }
            List<App> appList = appService.findAll();
            modelMap.put("appList", appList);

            List<Method> methodList = methodService.findAll();
            modelMap.put("methodList", methodList);

            modelMap.put("errorMsg", ex.getMessage());
            return id == null ? "appMethod/app_method_add" : "appMethod/app_method_update";
        }

        return "redirect:/app-method/list";
    }

    @RequestMapping(value = "/update")
    public String appMethodUpdate(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        AppMethod appMethod = appMethodService.findById(id);
        modelMap.put("appMethod", appMethod);

        List<App> appList = appService.findAll();
        modelMap.put("appList", appList);

        List<Method> methodList = methodService.findAll();
        modelMap.put("methodList", methodList);

        return "appMethod/app_method_update";
    }


    @RequestMapping(value = "/delete")
    public String appMethodDelete(@RequestParam(value = "id") Integer id) {
        appMethodService.deleteById(id);

        return "redirect:/app-method/list";
    }

}
