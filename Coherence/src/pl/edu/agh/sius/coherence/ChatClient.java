package pl.edu.agh.sius.coherence;

import java.util.Scanner;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class ChatClient {
    
    private static final String KEY = "chat";
    
    private static class ChatListener implements MapListener {

        @Override
        public void entryDeleted(MapEvent arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void entryInserted(MapEvent arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void entryUpdated(MapEvent arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public static void main(String[] args) {
        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache("test");

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Name: ");
            String user = input.next();
            
            while (input.hasNext()) {
                String line = input.nextLine();
                
                ChatMessage message = new ChatMessage(user, line);
                cache.put(KEY, message);
            }
        }
    }

}
