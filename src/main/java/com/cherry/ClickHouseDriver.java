package com.cherry;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ClickHouseDriver extends BaseDriver {

    static{
        try {
            DriverManager.registerDriver(new ClickHouseDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
