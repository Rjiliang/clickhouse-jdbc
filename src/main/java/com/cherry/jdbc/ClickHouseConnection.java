package com.cherry.jdbc;

import com.cherry.jdbc.connect.ClientInfo;
import com.cherry.jdbc.connect.PhysicalConnection;
import com.cherry.jdbc.connect.PhysicalInfo;
import com.cherry.jdbc.connect.ServerInfo;
import com.cherry.jdbc.misc.Validate;
import com.cherry.jdbc.settings.ClickHouseConfig;
import com.cherry.jdbc.settings.ClickHouseDefines;
import com.cherry.jdbc.wrapper.SQLConnection;
import com.sun.security.ntlm.Client;

import java.net.InetSocketAddress;
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

    private static ClientInfo createClientInfo(PhysicalConnection connection, ClickHouseConfig config) throws SQLException {
        Validate.isTrue(connection.address() instanceof InetSocketAddress);
        InetSocketAddress address = (InetSocketAddress) connection.address();
        String clientName = String.format("%s %s",ClickHouseDefines.NAME,"client");
        String initialAddress = "[::ffff:127.0.0.1]:0";
        return  new ClientInfo(clientName,address.getHostName(),initialAddress);
    }

    private static ServerInfo createServerInfo(PhysicalConnection connection,ClickHouseConfig config) {
        //TODO
        try {
            long reversion = ClickHouseDefines.CLIENT_REVERSION;
            connection.sendHello("client",reversion,config.getDatabase(),config.getUsername(),config.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }


}
