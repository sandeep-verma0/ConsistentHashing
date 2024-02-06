package com.github.vsandeev.consistentHash;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ConsistentHash {

    /**
     *  Map of hash to server's virtual node
     */
    private final NavigableMap<Long, String> ring;

    /**
     * Number of virtual nodes or replicas against a given server
     */
    private final int numberOfReplicasOfAServer;

    /**
     * Instance of hash function used to generate hash
     */
    private final HashFunction hashFunction;

    /**
     * List of all physical servers storing data
     */
    List<Server> physicalServers;

    public ConsistentHash(List<Server> physicalServers, int numberOfReplicasOfAServer, HashFunction hashFunction) {
        this.ring= new TreeMap<>();
        this.physicalServers= physicalServers;
        this.numberOfReplicasOfAServer = numberOfReplicasOfAServer;
        this.hashFunction = hashFunction;
        for(Server server : physicalServers){
            addServer(server);
        }
    }

    /**
     * Distributes a server to ring via it's virtual nodes/replicas
     */
    public void addServer(Server server) {
        System.out.println("Adding server " + server.getName());
        for (int i = 0; i < numberOfReplicasOfAServer; i++) {
            long hash = this.hashFunction.hash(server.getName() + i);
            ring.put(hash, server.getName());
        }
    }


    /**
     * Removes a server's virtual nodes/replicas from the ring
     */
    public void removeServer(String server) {
        for (int i = 0; i < numberOfReplicasOfAServer; i++) {
            long hash = this.hashFunction.hash(server + i);
            ring.remove(hash);
        }
    }


    /**
     * Get the server's name from the given key on which key/data resides
     * Returns the server's name on which this key resides by going in clockwise direction
     */
    public String getServerName(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        long hash = this.hashFunction.hash(key);
        Long keyHash;
        if(ring.ceilingKey(hash)==null){
            keyHash=  ring.firstKey();
        }else{
            keyHash=ring.ceilingKey(hash);
        }
        return ring.get(keyHash);
    }

}
