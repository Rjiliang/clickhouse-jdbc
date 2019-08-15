package com.cherry.jdbc.protocol;

import com.cherry.jdbc.serializer.BinarySerializer;
import com.cherry.jdbc.settings.ClickHouseDefines;

import java.io.IOException;
import java.sql.SQLException;

public class HelloRequest extends RequestOrResponse {

    private final String clientName;
    private final long clientReversion;
    private final String defaultDatabase;
    private final String clientUsername;
    private final String clientPassword;

    public HelloRequest(String clientName, long clientReversion, String defaultDatabase, String clientUsername, String clientPassword) {
        super(ProtocolType.REQUEST_HELLO);
        this.clientName = clientName;
        this.clientReversion = clientReversion;
        this.defaultDatabase = defaultDatabase;
        this.clientUsername = clientUsername;
        this.clientPassword = clientPassword;
    }

    @Override
    public void writeImpl(BinarySerializer serializer) throws IOException, SQLException {
        serializer.writeStringBinary(ClickHouseDefines.NAME +" "+clientName);
        serializer.writeVarInt(ClickHouseDefines.MAJOR_VERSION);
        serializer.writeVarInt(ClickHouseDefines.MINOR_VERSION);
        serializer.writeVarInt(clientReversion);
        serializer.writeStringBinary(defaultDatabase);
        serializer.writeStringBinary(clientUsername);
        serializer.writeStringBinary(clientPassword);
    }
}
