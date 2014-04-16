package pl.edu.agh.sius.coherence;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CachePopulator {
    
    private static final Random random = new Random();
    private static final String[] GENDERS = { "M", "F" };
    
    private static final int ITEM_COUNT = 10000;
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");

        Map<String, Person> people = new HashMap<String, Person>();
        for (int i = 0; i < ITEM_COUNT; ++ i) {
            Person p = randomPerson(i);
            System.out.println(p);
            people.put(p.getSurname(), p);
        }
        cache.putAll(people);
    }
    
    private static Person randomPerson(int id) {
        String name = randomString(10, 4);
        String surname = randomString(17, 3);
        String address = randomString(10, 4);
        String gender = GENDERS[random.nextInt(2)];
        int age = 10 + random.nextInt(70);
        
        return new Person(id, surname, name, address, age, gender);
    }
    
    private static String randomString(int avgLength, int deviation) {
        int dev = random.nextInt(2 * deviation + 1) - deviation;
        int length = avgLength + dev;
        char[] chars = new char[length];
        
        for (int i = 0; i < chars.length; ++ i) {
            chars[i] = (char) (random.nextInt('z' - 'a' + 1) + 'a');
        }
        return new String(chars);
    }
}
