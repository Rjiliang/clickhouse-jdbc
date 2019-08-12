package com.cherry.jdbc.buffer;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

import java.io.IOException;

public class CompressedBuffedWriter implements BufferWriter {

    private int position;
    private int capacity;

    private final byte[] writtenBuf;
    private final BufferWriter writer;
    private final LZ4Compressor lz4Compressor = LZ4Factory.safeInstance().fastCompressor();

    public CompressedBuffedWriter(int capacity, BufferWriter writer) {
        this.capacity = capacity;
        this.writtenBuf = new byte[capacity];
        this.writer = writer;
    }

    @Override
    public void writeBinary(byte byt) throws IOException {
        writtenBuf[position++] = byt;
        flushToTarget(false);
    }

    @Override
    public void writeBinary(byte[] bytes) throws IOException {
        writeBinary(bytes,0,bytes.length);
    }

    @Override
    public void writeBinary(byte[] bytes, int offset, int length) throws IOException {
        for (int i = offset, max = offset +length ; i < max;) {
            if (remaining()) {
                int writtenNumber = Math.min(capacity - position,max -1);
                System.arraycopy(bytes,i,writtenBuf,position,writtenNumber);
                i += writtenNumber;
                position += writtenNumber;
            }
            flushToTarget(false);
        }
    }

    @Override
    public void flushToTarget(boolean force) throws IOException {
       //TODO
    }

    private boolean remaining() {
        return position < capacity;
    }

}
