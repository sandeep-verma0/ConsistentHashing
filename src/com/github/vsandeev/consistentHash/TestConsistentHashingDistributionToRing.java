package com.github.vsandeev.consistentHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConsistentHashingDistributionToRing {

    public static void main(String[] args) {
        HashFunction hashFunction= new MD5HashFunction();
        Server server0= new Server(8080, "server-0", "10.10.10.10" );
        Server server1= new Server(8081, "server-1", "10.10.10.11" );
        Server server2= new Server(8082, "server-2", "10.10.10.12" );
        List<Server> servers= new ArrayList<>();
        servers.add(server0);
        servers.add(server1);
        servers.add(server2);
        ConsistentHash consistentHash= new ConsistentHash(servers, 50, hashFunction);

        Map<String, List<Integer>> serverToKeysMap=new HashMap<>();
        for(int i=0;i<10000;i++){
           String serverName = consistentHash.getServerName(i+"padding");
            serverToKeysMap.putIfAbsent(serverName, new ArrayList<>());
            serverToKeysMap.get(serverName).add(i);
       }

        System.out.println(" Count of keys on server 0  : " + serverToKeysMap.get(server0.getName()).size());
        System.out.println(" Count of keys on server 1  : "  + serverToKeysMap.get(server1.getName()).size());
        System.out.println(" Count of keys on server 2  : " + serverToKeysMap.get(server2.getName()).size());
    }
}
