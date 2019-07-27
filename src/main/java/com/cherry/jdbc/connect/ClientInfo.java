package com.cherry.jdbc.connect;

public class ClientInfo {

    private final String clientName;
    private final String clientHostName;
    private final String initialAssress;

    public ClientInfo(String clientName, String clientHostName, String initialAssress) {
        this.clientName = clientName;
        this.clientHostName = clientHostName;
        this.initialAssress = initialAssress;
    }
}
