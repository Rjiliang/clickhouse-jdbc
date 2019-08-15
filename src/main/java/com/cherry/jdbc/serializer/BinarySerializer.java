package com.cherry.jdbc.serializer;

import com.cherry.jdbc.buffer.BufferWriter;
import com.cherry.jdbc.buffer.CompressedBuffedWriter;
import com.cherry.jdbc.buffer.SocketBuffedWriter;
import com.cherry.jdbc.misc.Container;
import com.cherry.jdbc.settings.ClickHouseDefines;

import java.io.IOException;
import java.net.Socket;

public class BinarySerializer {
    private final Container<BufferWriter> container;

    public BinarySerializer(Socket socket) throws IOException {
        SocketBuffedWriter socketWriter = new SocketBuffedWriter(socket);
        container = new Container<BufferWriter>(socketWriter,
                new CompressedBuffedWriter(ClickHouseDefines.DEFAULT_BUFFER_SIZE, socketWriter));
    }

    public void writeStringBinary(String string) throws IOException {
        byte[] bytes = string.getBytes();
        writeVarInt(bytes.length);
    }

    public void writeVarInt(long x) throws IOException {
        for (int i = 0; i < 9; i++) {
            byte byt = (byte)(x & 0x7F);

            if (x > 0x7F) {
                byt |= 0x80;
            }
            x >>= 7;
            container.get().writeBinary(byt);
            if (x == 0) {
                return;
            }

        }
    }

    public void flushToTarget(boolean force) throws IOException {
        container.get().flushToTarget(force);
    }

    public static void main(String[] args){
        String str = "test serializer";
        byte[] bytes = str.getBytes();
        System.out.println("test before:");
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        System.out.println("after");

        for (int i = 0; i < 9; i++) {
            byte x = (byte)(bytes.length & 0x7F);
            if (x > 0x7f) {
                x |= 0x80;
            }
            x >>= 7;
            System.out.println(x);
            if ((x == 0)) {
                return;
            }
        }
    }
}
