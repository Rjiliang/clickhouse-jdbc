package com.cherry.jdbc;

import com.cherry.jdbc.connect.ClientInfo;
import com.cherry.jdbc.connect.PhysicalConnection;
import com.cherry.jdbc.connect.PhysicalInfo;
import com.cherry.jdbc.connect.ServerInfo;
import com.cherry.jdbc.settings.ClickHouseConfig;
import com.cherry.jdbc.wrapper.SQLConnection;
import com.sun.security.ntlm.Client;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ClickHouseConnection extends SQLConnection {

    private final AtomicBoolean isClosed;
    private final ClickHouseConfig configure;
    private final AtomicReference<PhysicalInfo> atomicInfo;

    protected ClickHouseConnection(ClickHouseConfig config, PhysicalInfo info) {
        this.isClosed = new AtomicBoolean(false);
        this.configure = config;
        this.atomicInfo = new AtomicReference<PhysicalInfo>(info);
    }

    public static ClickHouseConnection createClickHouseConnection(ClickHouseConfig config) throws SQLException {
        return new ClickHouseConnection(config,createPhysicalInfo(config));
    }


    private static PhysicalInfo createPhysicalInfo(ClickHouseConfig config) throws SQLException {
        PhysicalConnection  physicalConnection = PhysicalConnection.openPhysicalConnection(config);
        return  new PhysicalInfo(createClientInfo(physicalConnection,config),createServerInfo(physicalConnection,config),physicalConnection);
    }

    private static ClientInfo createClientInfo(PhysicalConnection connection, ClickHouseConfig config) {
        //TODO
        return  null;
    }

    private static ServerInfo createServerInfo(PhysicalConnection connection,ClickHouseConfig config) {
        //TODO
        return  null;
    }

}
