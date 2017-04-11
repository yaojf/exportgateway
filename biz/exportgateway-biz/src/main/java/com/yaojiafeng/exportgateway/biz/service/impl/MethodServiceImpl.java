package com.yaojiafeng.exportgateway.biz.service.impl;

import com.yaojiafeng.exportgateway.biz.service.MethodService;
import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.MethodDao;
import com.qccr.exportgateway.dal.entity.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:36 $
 */
@Service
public class MethodServiceImpl implements MethodService {

    @Autowired
    private MethodDao methodDao;


    @Override
    public int deleteById(Integer id) {
        return methodDao.deleteById(id);
    }

    @Override
    public Method findById(Integer id) {
        return methodDao.findById(id);
    }

    @Override
    public int saveMethod(Method method) {
        if (method.getId() == null) {
            return methodDao.insertMethod(method);
        } else {
            return methodDao.updateMethod(method);
        }
    }

    @Override
    public List<Method> findMethodList(Byte status, String name, Integer systemId, Page page) {
        return methodDao.findMethodList(status, name, systemId, page);
    }

    @Override
    public List<Method> findAll() {
        return methodDao.findAll();
    }
}
