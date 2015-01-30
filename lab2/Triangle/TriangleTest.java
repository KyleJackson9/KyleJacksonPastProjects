/*
 * JUnit tests for the Triangle class
 */
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author melaniecebula
 */
public class TriangleTest {
  /**  We've already created a testScalene method.  Please fill in testEquilateral, and additionally
   *   create tests for Isosceles, Negative Sides, and Invalid sides
   **/

    @Test
    public void testScalene() {
        Triangle t = new Triangle(30, 40, 50);
        String result = t.triangleType();
        assertEquals("Scalene", result);
    }

    @Test
    public void testEquilateral() {
            Triangle t = new Triangle(30, 30, 30);
        String result = t.triangleType();
        assertEquals("Equilateral", result);//TODO: FILL IN
    }
    @Test
    public void testIsosceles() {
        Triangle t = new Triangle(5, 5, 4);
        String result = t.triangleType();
        assertEquals("Isosceles", result);//TODO: FILL IN
    }

    @Test
    public void testNegativeSides() {
        Triangle t = new Triangle(-30, 40, 50);
        String result = t.triangleType();
        assertEquals("At least one length is less than 0!", result);//TODO: FILL IN
    }
    @Test
    public void testInvalidSides() {
        Triangle t = new Triangle(300, 40, 50);
        String result = t.triangleType();
        assertEquals("The lengths of the triangles do not form a valid triangle!", result);//TODO: FILL IN
    }

    //TODO: CREATE MORE TESTS

    public static void main(String[] args) {
      //TODO: RUN TESTS (Look in ArithmeticTest.java main method for help!)
      jh61b.junit.textui.runClasses(TriangleTest.class);

    }
}
