
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPiece {
	
	@Test
	public void testIsFire(){
		Piece p = new Piece(true,Board b, 8,1,"shield");
		assertEquals(true, p.isFire());

	}

	@Test 
	public void testSide(){
		Piece p = new Piece(true,Board b, 8,1,"shield");
		assertEquals(0, p.side());
	}

}