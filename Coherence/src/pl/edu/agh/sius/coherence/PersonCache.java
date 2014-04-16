package pl.edu.agh.sius.coherence;

import java.util.Map.Entry;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class PersonCache {
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");
        
        Person[] people = new Person[]{
            new Person(1, "Hitler", "Adolf", "Berlin", 56, "M"),
            new Person(2, "Stalin", "Joseph", "Moscow", 75, "M"),
            new Person(3, "Mussolini", "Benito", "Rome", 62, "M"),
            new Person(4, "Zedong", "Mao", "Bejing", 83, "M"),
            new Person(5, "Il Sung", "Kim", "Pyongyang", 82, "M")
        };
        
        for (Person person : people) {
            cache.put(person.getSurname(), person);
        }
        
        Person stalin = (Person) cache.get("Stalin");
        
        System.out.println("Stalin from the cache:");
        System.out.println(stalin);
        
        System.out.println("Equal: " + stalin.equals(people[1]));

        @SuppressWarnings("unchecked")
        Set<Entry<String, Person>> entries = (Set<Entry<String, Person>>) cache
                .entrySet(null, null);

        System.out.println("People retrieved from the cache:");
        for (Entry<String, Person> person : entries) {
            System.out.println(person.getKey());
        }
        System.out.println("- - - - - - - - - - - - - - - -");
    }

}
