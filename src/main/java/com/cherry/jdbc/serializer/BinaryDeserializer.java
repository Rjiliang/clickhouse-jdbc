package com.cherry.jdbc.serializer;

import com.cherry.jdbc.buffer.BuffedReader;
import com.cherry.jdbc.buffer.SocketBuffedReader;
import com.cherry.jdbc.misc.Container;

import java.io.IOException;
import java.net.Socket;

public class BinaryDeserializer {

    private final Container<BuffedReader> container;

    public BinaryDeserializer(Socket socket) throws IOException {
        SocketBuffedReader socketReader = new SocketBuffedReader(socket);
        //TODO CREATE CompressedBuffedReader
        container = null;

    }
}
