package com.cherry.jdbc.serializer;

import java.net.Socket;

public class BinarySerializer {

    public BinarySerializer(Socket socket) {

    }

    public void writeStringBinary(String string) {
        byte[] bytes = string.getBytes();
        writeVarInt(bytes.length);
    }

    private void writeVarInt(long x) {
        for (int i = 0; i < 9; i++) {

        }
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
