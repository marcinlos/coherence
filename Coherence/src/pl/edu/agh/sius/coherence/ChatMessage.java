package pl.edu.agh.sius.coherence;

public class ChatMessage {

    private final String user;
    
    private final String content;
    
    public ChatMessage(String user, String content) {
        this.user = user;
        this.content = content;
    }
    
    public String getUser() {
        return user;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return String.format("%s:  %s", user, content); 
    }
    
}
