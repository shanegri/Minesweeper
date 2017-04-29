package ms.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import ms.generator.Generator;
import ms.generator.MineAmtGrtrThnSpacesException;
import ms.generator.Tile;

public class GeneratorTests {
	
	Generator g;

	public GeneratorTests(){
		 g = new Generator();
	}
	
	@Test
	public void genBoolBoardTest() {
		boolean[][] b = g.genBoolBoard(9, 10, 10);
		int count = 0;
		for(boolean[] i : b){
			for(boolean j : i){
				if(j) count += 1;
			}		
		}
		assertEquals(9, count);
	}
	
	@Test
	public void genBoolBoardExceptionTest(){
		try {
			g.genStringBoard(10, 2, 2);
			fail();
		} catch (MineAmtGrtrThnSpacesException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void isInBoundsTest(){
		assertFalse(g.isInBnds(-1, 0, 10, 10));
		assertFalse(g.isInBnds(10, 7, 10, 10));
		assertFalse(g.isInBnds(2, 11, 10, 10));
		assertFalse(g.isInBnds(2, -3, 10, 10));
		assertTrue(g.isInBnds(2, 4, 10, 10));
	}
	
	@Test
	public void countAdjMinesTest(){
		boolean[][] b = new boolean[5][5];
		b[0][0] = true; b[1][0] = true;
						
						b[3][4] = true;
		b[4][3] = true; b[4][4] = true;
		assertEquals(2, g.countAdjMines(b, 0, 1));
		assertEquals(1, g.countAdjMines(b, 2, 0));
		assertEquals(3, g.countAdjMines(b, 3, 3));
		assertEquals(0, g.countAdjMines(b, 4, 0));
	}
	
	@Test
	public void genSurMinesTest(){
		String[][] s = g.genStringBoard(15, 10, 10);
		int count = 0;
		for(String[] i : s){
			for(String j : i){
				if(j.equals("X")) count += 1;
			}
			
		}
		assertEquals(15, count);
	}
	
	@Test
	public void genTileBoardTest(){
		int count = 0;
		Tile[][] x = g.genTileBoard(9, 10, 10);
		for(Tile[] i : x){
			for(Tile j : i){
				if(j.isMine()) count += 1;
			}
			
		}
		assertEquals(10, x.length);
		assertEquals(10, x[0].length);
		assertEquals(9, count);
	}

}
