package pl.edu.agh.sius.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.aggregator.DoubleAverage;
import com.tangosol.util.aggregator.DoubleMax;
import com.tangosol.util.filter.AlwaysFilter;
import com.tangosol.util.filter.EqualsFilter;

public class AggregationExample {
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");

        DoubleAverage ageAvgAggr = new DoubleAverage("getAge");
        
        double maleAvgAge = (Double) cache.aggregate(
                new EqualsFilter("getGender", "M"), ageAvgAggr);
        
        double femaleAvgAge = (Double) cache.aggregate(
                new EqualsFilter("getGender", "F"), ageAvgAggr);
        
        double avgAge = (Double) cache.aggregate(new AlwaysFilter(), ageAvgAggr);
        double maxAge = (Double) cache.aggregate(new AlwaysFilter(), new DoubleMax("getAge"));
        
        System.out.println("Avg male age:   " + maleAvgAge);
        System.out.println("Avg female age: " + femaleAvgAge);
        System.out.println("Avg age:        " + avgAge);
        System.out.println("Max age:        " + maxAge);
    }
}
