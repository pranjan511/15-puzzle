/**
 * Prabhat Ranjan
 * M.SC(CCN),Telecom SudParis
 * Date: 12/02/2016
 * */
package Test;
import Model.*;


import static org.junit.Assert.*;

import org.junit.Test;

public class TestPuzzle {
	PuzzleModel puzzleModel = new PuzzleModel();
	PuzzleModel SolvedModel = new PuzzleModel(4,4);
	@Test
	public void testExchangeTiles() {
		String A=puzzleModel.getFace(0, 1);
		String B=puzzleModel.getFace(2, 2);
		puzzleModel.exchangeTiles(0, 1, 2, 2);
		assertEquals(A, puzzleModel.getFace(2,2));
		assertEquals(B, puzzleModel.getFace(0,1));
	}
	@Test
	public void testGetFace(){
		
		assertEquals(SolvedModel.getFace(1, 1),"6");
		assertEquals(SolvedModel.getFace(2, 2), "11");
		assertEquals(SolvedModel.getFace(3,3),null);
	}
	
	
}