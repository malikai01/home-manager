package com.mlk.home.entity;

import com.mlk.home.base.BaseEntity;

import java.util.Date;

/**
 * @author malikai
 * @version 1.0
 * @date 2019-7-22 15:08
 */
public class ManagerConfig extends BaseEntity {

    private String configKey;
    private String configValue;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String memo;

    public ManagerConfig(String configKey, String configValue, Date createTime, Date updateTime, String createUser, String memo) {
        this.configKey = configKey;
        this.configValue = configValue;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.memo = memo;
    }

    public ManagerConfig() {
        super();
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
