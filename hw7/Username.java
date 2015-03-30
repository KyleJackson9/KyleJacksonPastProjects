public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    private String username;
    private int userHash;

    public Username() {
        hashCode();
    }

    public Username(String reqName) {
        if (reqName == null){
            throw new NullPointerException("Requested username is null!");
        } else if (reqName.length() != 3){
            throw new IllegalArgumentException("bad dog");
        } else {
            String[] tokens = reqName.split("");
                char x = tokens[1].charAt(0);
                char y = tokens[2].charAt(0);
                char z = tokens[0].charAt(0);
                if (Character.isLetter(x) && Character.isLowerCase(x) && Character.isLetter(y) && Character.isUpperCase(y) && Character.isDigit(z)){
                    username = reqName;
                } else {
                throw new IllegalArgumentException("ugh");
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Username) {
            Username other = (Username) o;
         return this.username.equals(other.username);
     } else {
        return false;
     }
  
    }

    @Override
    public int hashCode() { 
        int a = ( 25 * (int)Math.random());
        int b = ( 25 * (int)Math.random());
        int i = (int) 10 * ((int) Math.random());
        userHash = a + 26*b + i;
        char j = (char) a;
        char k = (char) b;
        String x = Character.toString(k);
        x.toUpperCase();
        this.username = Integer.toString(i) + Character.toString(j) + x;
        return userHash;
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
    }
}