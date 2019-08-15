package com.cherry.jdbc.protocol;

import com.cherry.jdbc.serializer.BinarySerializer;

import java.io.IOException;
import java.sql.SQLException;

public class HelloResponse extends RequestOrResponse {

    private final long reversion;
    private final long majorVersion;
    private final long minorVersion;
    private final String serverName;
    private final String serverTimeZone;
    private final String serverDisplayName;

    public HelloResponse( long reversion, long majorVersion,
                         long minorVersion, String serverName, String serverTimeZone, String serverDisplayName) {
        super(ProtocolType.RESPONSE_HELLO);

        this.reversion = reversion;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.serverName = serverName;
        this.serverTimeZone = serverTimeZone;
        this.serverDisplayName = serverDisplayName;
    }



    public long getReversion() {
        return reversion;
    }

    public long getMajorVersion() {
        return majorVersion;
    }

    public long getMinorVersion() {
        return minorVersion;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }

    public String getServerDisplayName() {
        return serverDisplayName;
    }

    @Override
    public void writeImpl(BinarySerializer serializer) throws IOException, SQLException {
        throw new UnsupportedOperationException("HelloResponse Cannot write to Server.");
    }
}
