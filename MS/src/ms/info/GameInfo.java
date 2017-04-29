package ms.info;

public class GameInfo {
	
	private int mineAmt;
	private int xSize;
	private int ySize;
	private int remainingMines;
	private static GameInfo instance;
	
	private GameInfo(){
		mineAmt = 10;
		xSize = 9;
		ySize = 9;
		remainingMines = mineAmt;		
	}
	
	public static GameInfo getInstance(){
		if(instance == null) instance = new GameInfo();
		return instance;
	}
	
	public void beginner(){
		mineAmt = 10;
		xSize = 9;
		ySize = 9;
		remainingMines = 9;
	}
	
	public void intermediate(){
		mineAmt = 40;
		xSize = 16;
		ySize = 16;
		remainingMines = 9;
	}
	
	public void expert(){
		mineAmt = 99;
		xSize = 16;
		ySize = 30;
		remainingMines = 9;	
	}
	
	public void custom(int mineAmt, int xSize, int ySize){
		this.mineAmt = mineAmt;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public int getXSize(){
		return xSize;
	}
	
	public int getYSize(){
		return ySize;
	}
	
	public int getMineAmt(){
		return mineAmt;
	}

}
