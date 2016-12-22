/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.mapping;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:50 $
 */
public class MethodInfo {
    public static final String SEPERATOR = ".";

    private String name;

    private String systemName;

    public MethodInfo(String name, String systemName) {
        this.name = name;
        this.systemName = systemName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSystemName() == null) ? 0 : getSystemName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }

        MethodInfo other = (MethodInfo) that;

        return (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getSystemName() == null ? other.getSystemName() == null : this.getSystemName().equals(other.getSystemName()));
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "name='" + name + '\'' +
                ", systemName='" + systemName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 合并methodinfo
     *
     * 类上method注解可以配置公共的方法前缀name,例如com.alipay
     *
     * 方法上配置后缀,例如 query.order
     * 配置systemName会覆盖方法注解的配置
     *
     *
     * @param info
     * @return
     */
    public MethodInfo combine(MethodInfo info) {
        String name = "";
        String systemName = "";
        if (StringUtils.isNotBlank(this.getName()) && this.getName().endsWith(SEPERATOR)) {
            name = this.getName() + info.getName();
        } else if (StringUtils.isNotBlank(this.getName())) {
            name = this.getName() + SEPERATOR + info.getName();
        } else {
            name = info.getName();
        }

        if (StringUtils.isNotBlank(info.getSystemName())) {
            systemName = info.getSystemName();
        } else {
            systemName = this.getSystemName();
        }

        return new MethodInfo(name, systemName);
    }

    public boolean isAnyBlank() {
        if (StringUtils.isAnyBlank(name, systemName)) {
            return true;
        }
        return false;
    }


    public static class Builder {
        private String name;

        private String systemName;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder systemName(String systemName) {
            this.systemName = systemName;
            return this;
        }

        public MethodInfo build() {
            return new MethodInfo(this.name, this.systemName);
        }
    }
}
