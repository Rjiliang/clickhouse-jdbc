package com.cherry.jdbc.settings;

/**
 * 配置项
 */
public enum SettingKey {

    database(ISettingType.String,""),
    port(ISettingType.Int32,"" ),
    address(ISettingType.String, "");

    private final ISettingType type;
    private final String describe;

    SettingKey(ISettingType type,String describe) {
        this.type = type;
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public ISettingType getType() {
        return type;
    }
}
