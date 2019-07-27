package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchQuery {

    public static void main(String[] args) throws Exception {
        Class.forName("com.cherry.ClickHouseDriver");
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.1.10");
        PreparedStatement pstm = connection.prepareStatement("create database demo");
        pstm.execute();
        pstm.close();
        connection.close();
    }

}
