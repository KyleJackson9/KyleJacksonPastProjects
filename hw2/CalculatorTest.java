import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        //tester = new StaffCalculator(); // Comment me out to test your Calculator
         tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS

    @Test
    public void testAdd() {
        int result = tester.add(3,9);
        assertEquals(12, result);
    }

    @Test
    public void testAddNegatives() {
        int result = tester.add(-3,-9);
        assertEquals(-12, result);
    }

    @Test
    public void testAddMix() {
        int result = tester.add(-3,9);
        assertEquals(6, result);
    }

    @Test
    public void testMultMix() {
        int result = tester.multiply(-3,2);
        assertEquals(-6, result);
    }

    @Test
    public void testMultNeg() {
        int result = tester.multiply(-3,-2);
        assertEquals(6, result);
    }

    @Test
    public void testMultPos() {
        int result = tester.multiply(3,2);
        assertEquals(6, result);
    }

    @Test
    public void testPrint(){
        tester.saveEquation("1 + 6", 7);
        tester.printHistory(1);
    }

    @Test
    public void testAllPrint(){
        tester.saveEquation("1 + 90", 91);
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 99", 100);
        tester.printAllHistory();
    }

    @Test
    public void testClear(){
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 5", 6);
        tester.clearHistory();
        tester.printAllHistory();
    }

    @Test
    public void testUndo(){
        tester.saveEquation("1 + 2", 3);
        tester.undoEquation();
        tester.saveEquation("1 + 5", 6);
        tester.printAllHistory();
    }

    @Test
    public void testSum(){
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 5", 6);
        System.out.println(tester.cumulativeSum());
    }

    @Test
    public void testMult(){
        tester.saveEquation("1 + 2", 3);
        tester.saveEquation("1 + 5", 6);
        System.out.println(tester.cumulativeProduct());
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}