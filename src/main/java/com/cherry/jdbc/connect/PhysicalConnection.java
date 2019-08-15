package com.cherry.jdbc.connect;

import com.cherry.jdbc.protocol.HelloRequest;
import com.cherry.jdbc.serializer.BinaryDeserializer;
import com.cherry.jdbc.serializer.BinarySerializer;
import com.cherry.jdbc.settings.ClickHouseConfig;
import com.cherry.jdbc.settings.ClickHouseDefines;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;

public class PhysicalConnection {
    private final Socket socket;
    private final SocketAddress address;
    private final BinarySerializer serializer;
    private final BinaryDeserializer deserializer;

    public PhysicalConnection(Socket socket, BinarySerializer serializer, BinaryDeserializer deserializer) {
        this.socket = socket;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.address = socket.getLocalSocketAddress();
    }

    public static PhysicalConnection openPhysicalConnection(ClickHouseConfig config) throws SQLException {

        try {
            SocketAddress endpoint = new InetSocketAddress(config.getAddress(), config.getPort());

            Socket socket = new Socket();
            socket.setTcpNoDelay(true);
            socket.setSendBufferSize(ClickHouseDefines.DEFAULT_BUFFER_SIZE);
            socket.setReceiveBufferSize(ClickHouseDefines.DEFAULT_BUFFER_SIZE);

            socket.connect(endpoint,config.getConnectTimeout());
            return  new PhysicalConnection(socket,new BinarySerializer(socket),new BinaryDeserializer(socket));
        } catch (IOException e) {
            throw  new SQLException(e.getMessage(),e);
        }
    }


    public SocketAddress address() {
        return  address;
    }

    public void sendHello(String client, long reversion, String db, String user, String password) throws SQLException {
        sendRequest(new HelloRequest(client,reversion,db,user,password));
    }

    private void sendRequest(HelloRequest helloRequest) throws SQLException {
        try {
            helloRequest.writeTo(serializer);
            serializer.flushToTarget(true);
        } catch (IOException e) {
            throw new SQLException(e.getMessage(),e);
        }
    }



}
