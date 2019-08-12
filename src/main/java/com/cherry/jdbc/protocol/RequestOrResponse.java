package com.cherry.jdbc.protocol;

import com.cherry.jdbc.serializer.BinarySerializer;

public class RequestOrResponse {

    private final ProtocolType type;

    public ProtocolType type() {
        return type;
    }

    RequestOrResponse(ProtocolType type) {
        this.type = type;
    }


    public void  writeTo(BinarySerializer serializer) {

    }

}
