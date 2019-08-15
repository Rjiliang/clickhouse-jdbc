package com.cherry.jdbc.buffer;

import java.io.IOException;

public interface BuffedReader {
    int readBinary()throws IOException;

    int readBinary(byte[] bytes) throws IOException;
}
