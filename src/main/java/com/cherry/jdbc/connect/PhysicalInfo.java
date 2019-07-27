package com.cherry.jdbc.connect;

public class PhysicalInfo {
   private final ClientInfo clientInfo;
   private final ServerInfo serverInfo;
   private final PhysicalConnection connection;


    public PhysicalInfo(ClientInfo clientInfo, ServerInfo serverInfo, PhysicalConnection connection) {
        this.clientInfo = clientInfo;
        this.serverInfo = serverInfo;
        this.connection = connection;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public PhysicalConnection getConnection() {
        return connection;
    }
}
