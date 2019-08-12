package com.cherry.jdbc.protocol;

public enum ProtocolType {
    REQUEST_HELLO(0);

    private final int id;

    ProtocolType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
