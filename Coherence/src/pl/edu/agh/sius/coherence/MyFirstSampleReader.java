package pl.edu.agh.sius.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class MyFirstSampleReader {

    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        System.out.println("Value in cache is " + cache.get("key"));
    }
    
}
