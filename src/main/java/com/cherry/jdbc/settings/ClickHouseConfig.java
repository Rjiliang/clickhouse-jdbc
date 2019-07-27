package com.cherry.jdbc.settings;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClickHouseConfig {

    private int port;

    private String address;

    private String database;

    private String username;

    private String password;

    private int soTimeout;

    private int connectTimeout;

    private Map<SettingKey,Object> settings;

    static final Pattern DB_PATH_PATTERN = Pattern.compile("/([a-zA-Z0-9_])");

    private ClickHouseConfig() {

    }

    public ClickHouseConfig(String url, Properties properties){
        this.settings = parseJDBCUrl(url);

    }

    private Map<SettingKey,Object> parseJDBCUrl(String jdbcUrl) {
        HashMap<SettingKey, Object> settings = new HashMap<>();
        try {
            URI uri = new URI(jdbcUrl.substring(5));

            String database = uri.getPath();
            if (database != null && !database.isEmpty()) {
                Matcher m = DB_PATH_PATTERN.matcher(database);
                if (m.matches()) {
                    settings.put(SettingKey.database,m.group(1));
                }
            }

            settings.put(SettingKey.port,uri.getPort());
            settings.put(SettingKey.address,uri.getPath());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Map<SettingKey, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<SettingKey, Object> settings) {
        this.settings = settings;
    }
}
