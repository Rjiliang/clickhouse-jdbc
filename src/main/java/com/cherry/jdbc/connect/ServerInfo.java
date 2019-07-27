package com.cherry.jdbc.connect;

import com.cherry.jdbc.settings.ClickHouseConfig;

import java.util.TimeZone;

public class ServerInfo {
    private final long reversion;
    private final TimeZone timeZone;
    private final String displayName;
    private final ClickHouseConfig config;

    public ServerInfo(long reversion, TimeZone timeZone, String displayName, ClickHouseConfig config) {
        this.reversion = reversion;
        this.timeZone = timeZone;
        this.displayName = displayName;
        this.config = config;
    }

    public long getReversion() {
        return reversion;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ClickHouseConfig getConfig() {
        return config;
    }
}
