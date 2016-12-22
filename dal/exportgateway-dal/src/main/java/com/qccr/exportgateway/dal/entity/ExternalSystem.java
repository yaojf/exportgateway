package com.qccr.exportgateway.dal.entity;

import java.io.Serializable;
import java.util.Date;

public class ExternalSystem implements Serializable {
    private Integer id;

    private String enName;

    private String zhName;

    private Byte status;

    private String rsaPrivateKey;

    private String rsaPublicKey;

    private String md5SecretKey;

    private Boolean signBack;

    private Date createTime;

    private Date updateTime;

    private String createPerson;

    private String updatePerson;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getMd5SecretKey() {
        return md5SecretKey;
    }

    public void setMd5SecretKey(String md5SecretKey) {
        this.md5SecretKey = md5SecretKey;
    }

    public Boolean getSignBack() {
        return signBack;
    }

    public void setSignBack(Boolean signBack) {
        this.signBack = signBack;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
}