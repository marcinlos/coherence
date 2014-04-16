package pl.edu.agh.sius.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class MyFirstSample {

    public static void main(String[] args) {
        // ensure we are in a cluster
        CacheFactory.ensureCluster();
        
        // create or get a named cache called mycache
        NamedCache cache = CacheFactory.getCache("test");

        // put key, value pair into the cache.
        cache.put("key", "value");
        System.out.println("Value in cache is " + cache.get("key"));
    }
}