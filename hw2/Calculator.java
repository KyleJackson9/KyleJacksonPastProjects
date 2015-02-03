import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int answer = x ^ y; // works if the numbers there aren't two 1s in the same column
        int holder = x & y; // holds the spots that have a 1 in both columns
        if (holder == 0){
            return answer;
        }
        else{
        while (holder != 0){

            int shift = holder << 1; //shift them all left one
            holder = shift & answer; // holds the 1 that couldnt be added; otherwise becomes all zeros and exits
            answer = answer ^ shift; // adds in the answer and fits in the 1s where there are zeros
        }
        return answer;
        }
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int answer = x; // x^y works for multiples of 2
        int ycheck = y >>> 31; //if first bit is 1 its negative
        int xcheck = x >>> 31;
        int i = y;

        if (xcheck == 1) {
            x = -x;
            answer = -answer;
            // x = x | -0;
        }
        if (ycheck == 1) {
            y = -y;
            i = -i;
        }
        while (i != 1) {
            answer = add(answer, x);
            i = add(i,-1);
        }
       
        if (ycheck == xcheck) {
            return answer;
        } else {
            return -answer;
        }
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history); 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (history != null){
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i = n;
        while (i > 0){
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
            i = add(i,-1);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (history != null){
            history = history.next;
        }
        history = history;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        //EquationList x = history;
        int cumSum = 0;
        while (history != null){
            cumSum = add(cumSum, history.result);
            history = history.next;
        }
        return cumSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int cumProd = 1;
        while (history != null){
            cumProd = multiply(cumProd,history.result);
            history = history.next;
        }
        return cumProd;
    }
}