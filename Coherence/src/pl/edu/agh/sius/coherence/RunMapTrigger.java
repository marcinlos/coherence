package pl.edu.agh.sius.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapTrigger;
import com.tangosol.util.MapTriggerListener;

public class RunMapTrigger {

    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        
        MapTrigger trigger = new UppercaseMapTrigger();
        cache.addMapListener(new MapTriggerListener(trigger));
        
        Person stalin = new Person(2, "Stalin", "Joseph", "Moscow", 75, "M");
        cache.put(stalin.getSurname(), stalin);
        
        
        Person cachedStalin = (Person) cache.get("Stalin");
        
        System.out.println("Stalin from the cache:");
        System.out.println(cachedStalin);

    }
}
