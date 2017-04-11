package com.yaojiafeng.exportgateway.web.controller;

import com.yaojiafeng.exportgateway.biz.service.ExternalSystemService;
import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
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
@RequestMapping(value = "/external-system")
@Login
public class ExternalSystemController {

    @Autowired
    private ExternalSystemService externalSystemService;

    @RequestMapping(value = "/list")
    public String externalSystemList(@RequestParam(value = "status", defaultValue = "1") Byte status,
                                     @RequestParam(value = "enName", defaultValue = "") String enName,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     ModelMap modelMap) {
        Page page = new Page(pageNum, pageSize);

        List<ExternalSystem> externalSystemList = externalSystemService.findExternalSystemList(status, enName, page);
        modelMap.put("externalSystemList", externalSystemList);

        modelMap.put("page", page);
        modelMap.put("status", status);
        modelMap.put("enName", enName);

        return "externalSystem/external_system_list";
    }


    @RequestMapping(value = "/add")
    public String externalSystemAdd() {
        return "externalSystem/external_system_add";
    }

    @RequestMapping(value = "/save")
    public String externalSystemSave(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "en_name") String enName,
                                     @RequestParam(value = "zh_name") String zhName,
                                     @RequestParam(value = "status") Byte status,
                                     @RequestParam(value = "rsa_private_key", defaultValue = "") String rsaPrivateKey,
                                     @RequestParam(value = "rsa_public_key", defaultValue = "") String rsaPublicKey,
                                     @RequestParam(value = "md5_secret_key", defaultValue = "") String md5SecretKey,
                                     @RequestParam(value = "sign_back") Boolean signBack,
                                     ModelMap modelMap
    ) {
        ExternalSystem externalSystem = new ExternalSystem();
        externalSystem.setId(id);
        externalSystem.setEnName(enName);
        externalSystem.setZhName(zhName);
        externalSystem.setStatus(status);
        externalSystem.setRsaPrivateKey(rsaPrivateKey);
        externalSystem.setRsaPublicKey(rsaPublicKey);
        externalSystem.setMd5SecretKey(md5SecretKey);
        externalSystem.setSignBack(signBack);
        try {
            externalSystemService.saveExternalSystem(externalSystem);
        } catch (Exception ex) {
            if (id != null) {
                ExternalSystem externalSystem1 = externalSystemService.findById(id);
                modelMap.put("externalSystem", externalSystem1);
            }
            modelMap.put("errorMsg", ex.getMessage());

            return id == null ? "externalSystem/external_system_add" : "externalSystem/external_system_update";
        }

        return "redirect:/external-system/list";
    }


    @RequestMapping(value = "/update")
    public String externalSystemUpdate(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        ExternalSystem externalSystem = externalSystemService.findById(id);

        modelMap.put("externalSystem", externalSystem);

        return "externalSystem/external_system_update";
    }


    @RequestMapping(value = "/delete")
    public String externalSystemDelete(@RequestParam(value = "id") Integer id) {
        externalSystemService.deleteById(id);

        return "redirect:/external-system/list";
    }
}
