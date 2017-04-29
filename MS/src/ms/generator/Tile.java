package ms.generator;

public class Tile {
	
	private boolean mine;
	private int surMines;
	private boolean visible;
	private boolean isFlag;
	private boolean panOver;
	
	public Tile(boolean m, int s){
		mine = m;
		surMines = s;
		visible = false;
		isFlag = false;
	}
	
	public boolean isMine(){
		return mine;
	}
	
	public int getSurMines(){
		return surMines;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public boolean isVisible(boolean b){
		 visible = b;
		 return visible;
	}
	
	public boolean isFlag(){
		return isFlag;
	}
	
	public boolean isFlag(boolean b){
		isFlag = b;
		return isFlag;
	}
	
	public boolean panOver(){
		return panOver;
	}
	
	public boolean panOver(boolean b){
		panOver = b;
		return panOver;
	}
	
	
	
	
	
	

}
