

package synthesizer;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Julian Early
 */

public class TestArrayRingBuffer {
	ArrayRingBuffer arb1;

	@Before
	public void setUp()
	{
		arb1 = new synthesizer.ArrayRingBuffer(1);
	}

    @Test
    public void enqueueTests() {
        assertEquals(true,arb1.isEmpty());
        assertEquals(false,arb1.isFull());
        arb1.enqueue(0.0);
        try
        {
        	arb1.enqueue(1);
        	fail("Expected RuntimeException");
        }
        catch(RuntimeException aRuntimeException)
        {
        	assertThat(aRuntimeException.getMessage(), is("Ring buffer overflow"));
        }
        assertEquals(true,arb1.isFull());
        assertEquals(false,arb1.isEmpty());
    }

    @Test
    public void dequeueTest()
    {
    	arb1.enqueue(5);
    	assertEquals(false,arb1.isEmpty());
    	assertThat(arb1.dequeue(),is(5.0));
    	try
    	{
    		arb1.dequeue();
    		fail("Expected RuntimeException");
    	}
    	catch(RuntimeException aRuntimeException)
    	{
    		assertThat(aRuntimeException.getMessage(), is("Ring buffer underflow"));
    	}
    }

    @Test
    public void peekTest()
    {
    	try
    	{
    		arb1.peek();
    		fail("Expected RuntimeException");
    	}
    	catch(RuntimeException aRuntimeException)
    	{
    		assertThat(aRuntimeException.getMessage(), is("Ring buffer underflow"));
    	}
    	arb1.enqueue(5);
    	assertEquals(false,arb1.isEmpty());
    	assertThat(arb1.peek(),is(5.0));
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 