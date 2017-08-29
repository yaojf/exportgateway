package com.yaojiafeng.exportgateway.web.controller;

import com.yaojiafeng.exportgateway.biz.service.ExternalSystemService;
import com.yaojiafeng.exportgateway.biz.service.MethodService;
import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.ExternalSystem;
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
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:40 $
 */
@Controller
@RequestMapping("/method")
@Login
public class MethodController {

    @Autowired
    private MethodService methodService;

    @Autowired
    private ExternalSystemService externalSystemService;


    @RequestMapping(value = "/list")
    public String methodList(@RequestParam(value = "status", defaultValue = "1") Byte status,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "systemId", required = false) Integer systemId,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             ModelMap modelMap) {
        Page page = new Page(pageNum, pageSize);

        List<Method> methodList = methodService.findMethodList(status, name, systemId, page);
        modelMap.put("methodList", methodList);

        modelMap.put("page", page);
        modelMap.put("status", status);
        modelMap.put("name", name);
        modelMap.put("systemId", systemId);


        List<ExternalSystem> externalSystemList = externalSystemService.findAll();
        modelMap.put("externalSystemList", externalSystemList);


        return "method/method_list";
    }


    @RequestMapping(value = "/add")
    public String methodAdd(ModelMap modelMap) {
        List<ExternalSystem> externalSystemList = externalSystemService.findAll();
        modelMap.put("externalSystemList", externalSystemList);

        return "method/method_add";
    }

    @RequestMapping(value = "/save")
    public String methodSave(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "system_id") Integer systemId,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "url") String url,
                             @RequestParam(value = "sign_type") String signType,
                             @RequestParam(value = "status") Byte status,
                             @RequestParam(value = "remark") String remark,
                             @RequestParam(value = "invoke_type") Byte invokeType
    ) {

        Method method = new Method();
        method.setId(id);
        method.setSystemId(systemId);
        method.setName(name);
        method.setUrl(url);
        method.setSignType(signType);
        method.setStatus(status);
        method.setRemark(remark);
        method.setInvokeType(invokeType);

        methodService.saveMethod(method);

        return "redirect:/method/list";
    }

    @RequestMapping(value = "/update")
    public String methodUpdate(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        List<ExternalSystem> externalSystemList = externalSystemService.findAll();
        modelMap.put("externalSystemList", externalSystemList);

        Method method = methodService.findById(id);
        modelMap.put("method", method);

        return "method/method_update";
    }


    @RequestMapping(value = "/delete")
    public String methodDelete(@RequestParam(value = "id") Integer id) {
        methodService.deleteById(id);

        return "redirect:/method/list";
    }
}
