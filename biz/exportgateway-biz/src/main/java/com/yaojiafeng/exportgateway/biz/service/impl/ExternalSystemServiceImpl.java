package com.yaojiafeng.exportgateway.biz.service.impl;

import com.yaojiafeng.exportgateway.biz.service.ExternalSystemService;
import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.ExternalSystemDao;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:36 $
 */
@Service
public class ExternalSystemServiceImpl implements ExternalSystemService {


    @Autowired
    private ExternalSystemDao externalSystemDao;


    @Override
    public List<ExternalSystem> findExternalSystemList(Byte status, String enName, Page page) {
        return externalSystemDao.findExternalSystemList(status, enName, page);
    }

    @Override
    public int saveExternalSystem(ExternalSystem externalSystem) {
        ExternalSystem externalSystem1 = externalSystemDao.findByEnName(externalSystem.getEnName());

        if (externalSystem.getId() == null) {
            if (externalSystem1 != null) {
                throw new RuntimeException("已经存在相同的配置,enName不可重复");
            }

            return externalSystemDao.insertExternalSystem(externalSystem);
        } else {
            if (!externalSystem1.getId().equals(externalSystem.getId())) {
                throw new RuntimeException("已经存在相同的配置,enName不可重复");
            }

            return externalSystemDao.updateExternalSystem(externalSystem);
        }
    }

    @Override
    public ExternalSystem findById(Integer id) {
        return externalSystemDao.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return externalSystemDao.deleteById(id);
    }

    @Override
    public List<ExternalSystem> findAll() {
        return externalSystemDao.findAll();
    }
}
