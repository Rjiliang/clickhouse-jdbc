package com.cherry;

import com.cherry.jdbc.settings.ClickHouseConfig;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 驱动基类
 */
public class BaseDriver implements Driver {

    public Connection connect(String url, Properties info) throws SQLException {
        ClickHouseConfig config = new ClickHouseConfig(url, info);
        return null;
    }


    public boolean acceptsURL(String url) throws SQLException {
        System.out.println("test");
        return false;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger(){
        return null;
    }
}
