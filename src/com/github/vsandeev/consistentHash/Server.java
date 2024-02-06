package com.github.vsandeev.consistentHash;

public class Server {

    private final String ipAddress;
    private final long port;

    private final String name;

    public Server(long port, String serverName, String ipAddress){
        this.port=port;
        this.ipAddress = ipAddress;
        this.name= serverName;
    }
    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public long getPort() {
        return port;
    }

}
