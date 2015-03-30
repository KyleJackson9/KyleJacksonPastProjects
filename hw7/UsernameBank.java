import java.util.Map;
import java.util.HashMap;

public class UsernameBank {

    // Instance variables (remember, they should be private!)
    private String username;
    private String email;
    private Map<String, String> bMap;
    private Map<String, String> opposite;
    private int userHash;

    public UsernameBank() {
        username = "";
        email = " ";
        generateUsername(username,email);
        Map<String, String> bMap = new HashMap<String, String>();
        Map<String, String> opposite = new HashMap<String, String>();
        bMap.put(username, email);
        opposite.put(email, username);
        
    }

    public void generateUsername(String username, String email) {
        int a = ( 25 * (int)Math.random());
        int b = ( 25 * (int)Math.random());
        int i = (int) 10 * ((int) Math.random());
        userHash = a + b + i;
        char j = (char) a;
        char k = (char) b;
        String x = Character.toString(k);
        x.toUpperCase();
        this.username = Integer.toString(i) + Character.toString(j) + x;
        this.email = email;
        
    }

    public String getEmail(String username) {
        // YOUR CODE HERE
        if (bMap.containsValue(username)){
           return bMap.get(username);
        } else {
            throw new NullPointerException();
        }
    }

    public String getUsername(String userEmail)  {
        // YOUR CODE HERE
        if (opposite.containsValue(userEmail)){
           return opposite.get(userEmail);
        } else {
            throw new NullPointerException();
        }
    }

    public Map<String, Integer> getBadEmails() {
        // YOUR CODE HERE
        return null;
    }

    public Map<String, Integer> getBadUsernames() {
        // YOUR CODE HERE
        return null;
    }

    public String suggestUsername() {
        // YOUR CODE HERE
        return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
        return 9;
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
        // YOUR CODE HERE
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
        // YOUR CODE HERE
    }
}