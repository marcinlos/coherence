package pl.edu.agh.sius.coherence;

import java.util.Set;
import java.util.Map.Entry;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.GreaterEqualsFilter;

public class QueryExample {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");

        Set<Entry<String, Person>> males = (Set<Entry<String, Person>>) cache
                .entrySet(new EqualsFilter("getGender", "M"));

        Set<Entry<String, Person>> malesOver35 = (Set<Entry<String, Person>>) cache
                .entrySet(new AndFilter(
                        new EqualsFilter("getGender", "M"),
                        new GreaterEqualsFilter("getAge", 35)));
        
        System.out.println("Total number of males:    " + males.size());
        System.out.println("Number of Males over 35:  " + malesOver35.size());
    }

}
