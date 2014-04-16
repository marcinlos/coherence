package pl.edu.agh.sius.coherence;

import java.util.Scanner;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.MapEventFilter;

public class PersonListener {
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        
        ValueExtractor getClassName = new ChainedExtractor("getClass.getName");
        
        String className = Person.class.getName();
        Filter isPerson = new EqualsFilter(getClassName, className);
        Filter onlyPeople = new MapEventFilter(MapEvent.ENTRY_INSERTED, isPerson);
        
        cache.addMapListener(new MapListener() {
            
            @Override
            public void entryUpdated(MapEvent event) {
                System.out.println("Person change:");
                System.out.println("Before:" + event.getOldValue());
                System.out.println("After:" + event.getNewValue());
            }
            
            @Override
            public void entryInserted(MapEvent event) {
                System.out.println("New person: " + event.getNewValue());
            }
            
            @Override
            public void entryDeleted(MapEvent event) {
                // empty
            }
        }, onlyPeople, false);
        
        try (Scanner input = new Scanner(System.in)) {
            input.nextLine();
        }
    }
}
