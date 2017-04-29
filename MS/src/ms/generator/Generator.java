package ms.generator;

import java.util.Random;

public class Generator {
	
	public Tile[][] genTileBoard(int mineAmt, int xSize, int ySize){
		Tile[][] retVal = new Tile[xSize][ySize];
		boolean[][] b = genBoolBoard(mineAmt, xSize, ySize);
		for(int i = 0; i < xSize ; i++){
			for(int j = 0; j < ySize ; j++){
				retVal[i][j] = new Tile(b[i][j], countAdjMines(b, i, j));
			}
		}
		return retVal;
	}
	
	public String[][] genStringBoard(int mineAmt, int xSize, int ySize){
		boolean[][] b = genBoolBoard(mineAmt, xSize, ySize);
		return genSurMines(b, b.length, b[0].length);
	}
	
	public boolean[][] genBoolBoard(int mineAmt, int xSize, int ySize){
		if(mineAmt > xSize * ySize){
			throw new MineAmtGrtrThnSpacesException();
		}
		boolean[][] retVal = new boolean[xSize][ySize];
		Random r = new Random();
		int count = mineAmt;
		while(count > 0){
			int x1 = r.nextInt(xSize);
			int y1 = r.nextInt(ySize);
			if(!retVal[x1][y1]){
				retVal[x1][y1] = true;
				count -= 1;
			} 
		}
		return retVal;
	}
	
	public String[][] genSurMines(boolean[][] b, int xSize, int ySize){
		String[][] retVal = new String[xSize][ySize];
		for(int i = 0 ; i < b.length ; i++){
			for(int j = 0 ; j < b[0].length ; j++){
				retVal[i][j] = String.valueOf(countAdjMines(b, i, j));
				if(b[i][j]) retVal[i][j] = "X";
			}	
		}	
		return retVal;
	}
	
	public int countAdjMines(boolean[][] b, int x, int y){
		int retVal = 0;
		int xS = b.length;
		int yS = b[0].length;
		int x1, y1;
		x1 = x + 1; y1 = y - 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x + 1; y1 = y    ; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x + 1; y1 = y + 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x    ; y1 = y - 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x    ; y1 = y + 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x - 1; y1 = y - 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x - 1; y1 = y    ; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		x1 = x - 1; y1 = y + 1; if(isInBnds(x1, y1, xS, yS) && b[x1][y1]) retVal += 1;
		return retVal;
	}
	
	public boolean isInBnds(int x, int y, int xS, int yS){
		boolean retVal = true;
		if(x >= xS || x < 0) retVal = false;
		if(y >= yS || y < 0) retVal = false;
		return retVal;
	}
	
	
}


