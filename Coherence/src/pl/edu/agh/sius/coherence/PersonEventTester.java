package pl.edu.agh.sius.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class PersonEventTester {
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        
        
        Person stalin = new Person(2, "Stalin", "Joseph", "Moscow", 75, "M");
        cache.put(stalin.getSurname(), stalin);
        
        // update
        stalin.setGender("F");
        cache.put(stalin.getSurname(), stalin);
        
        stalin.setFirstname("Józefa");
        cache.put(stalin.getSurname(), stalin);
    }

}
