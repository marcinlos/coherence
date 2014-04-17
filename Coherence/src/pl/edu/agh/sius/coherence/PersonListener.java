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
    
    private static final class FemaleNameChangeListener implements MapListener {
        @Override
        public void entryUpdated(MapEvent event) {
            Person oldPerson = (Person) event.getOldValue();
            Person newPerson = (Person) event.getNewValue();

            String oldName = oldPerson.getFirstname();
            String newName = newPerson.getFirstname();

            System.out.println("Changed female name: " + oldName + " -> "
                    + newName);
        }

        @Override
        public void entryInserted(MapEvent arg0) {}

        @Override
        public void entryDeleted(MapEvent arg0) {}
    }

    private static final class GeneralListener implements MapListener {
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
    }

    public static final class FemaleNameChanged extends MapEventFilter {
        
        private static final long serialVersionUID = 7497586996175773764L;

        public FemaleNameChanged() {
            super(MapEvent.ENTRY_UPDATED);
        }

        @Override
        public boolean evaluate(Object e) {
            MapEvent event = (MapEvent) e;

            if (super.evaluate(event)) {

                Person oldPerson = (Person) event.getOldValue();
                Person newPerson = (Person) event.getNewValue();

                String oldName = oldPerson.getFirstname();
                String newName = newPerson.getFirstname();

                return !oldName.equals(newName);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        
        ValueExtractor getClassName = new ChainedExtractor("getClass.getName");
        
        String className = Person.class.getName();
        Filter isPerson = new EqualsFilter(getClassName, className);
        Filter onlyPeople = new MapEventFilter(MapEvent.ENTRY_INSERTED | 
                MapEvent.ENTRY_UPDATED, isPerson);
        
        cache.addMapListener(new GeneralListener(), onlyPeople, false);
        
        Filter changedFemaleName = new FemaleNameChanged();
        
        cache.addMapListener(new FemaleNameChangeListener(), changedFemaleName, false);
        
        
        try (Scanner input = new Scanner(System.in)) {
            input.nextLine();
        }
    }
}
