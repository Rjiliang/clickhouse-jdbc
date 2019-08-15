package com.cherry.jdbc.protocol;

import com.cherry.jdbc.connect.ServerInfo;
import com.cherry.jdbc.serializer.BinaryDeserializer;
import com.cherry.jdbc.serializer.BinarySerializer;

import java.io.IOException;
import java.sql.SQLException;

public abstract class RequestOrResponse {

    private final ProtocolType type;

    public ProtocolType type() {
        return type;
    }

    RequestOrResponse(ProtocolType type) {
        this.type = type;
    }


    public void  writeTo(BinarySerializer serializer)throws IOException,SQLException {
        serializer.writeVarInt(type.getId());

        this.writeImpl(serializer);
    }

    public abstract void writeImpl(BinarySerializer serializer)throws IOException,SQLException;


    public static RequestOrResponse readFrom(BinaryDeserializer deserializer, ServerInfo serverInfo)
            throws IOException,SQLException {
       //todo readFrom
        return null;
    }

}
