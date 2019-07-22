package com.mlk.home.search;

import com.mlk.home.base.BaseSearchModel;

/**
 * @author malikai
 * @version 1.0
 * @date 2019-7-22 16:50
 */
public class ManagerConfigModel extends BaseSearchModel {
    private String configKey;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
}
