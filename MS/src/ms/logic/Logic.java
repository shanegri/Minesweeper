package ms.logic;

import java.awt.Point;
import ms.generator.Generator;
import ms.generator.Tile;
import ms.info.GameInfo;
import ms.ui.UserInterface;

public class Logic {
	
	private final BoundsSelector bs;
	private final Generator g;
	private final UserInterface ui;
	private Tile[][] board;
	private GameInfo info;
	private boolean isFirstClick;

	
	public Logic(UserInterface ui){
		info = GameInfo.getInstance();
		this.ui = ui;
		isFirstClick = true;
		bs = new BoundsSelector();
		g = new Generator();
		genBoard();
	}
	
	public void genBoard(){
		board = g.genTileBoard(info.getMineAmt(), info.getXSize(), info.getYSize());	
	}
	
	public void leftButtonClick(int x, int y){
		if(isFirstClick){
			while((board[x][y].getSurMines() != 0) || board[x][y].isMine()){
				board = g.genTileBoard(info.getMineAmt(), info.getXSize(), info.getYSize());
			}
			
			for(Point i : bs.tileExpand(x, y, board)){
				board[i.x][i.y].isVisible(true);
			}
			
			isFirstClick = false;
		} else {
			if(!board[x][y].isFlag()){
			for(Point i : bs.tileExpand(x, y, board)){
				board[i.x][i.y].isVisible(true);
			}
			if(board[x][y].isMine()) gameEnd();
			}
		}
		ui.update();
		isWin();
	}
	
	public void rightButtonClick(int x, int y){
		if(!board[x][y].isVisible() && !isFirstClick){
			boolean flag = board[x][y].isFlag();
			board[x][y].isFlag(!flag);
		}
		ui.update();
	}
	
	public void enter(int x, int y){
		for(Point i : bs.getAdjTiles(x, y, board)){
			board[i.x][i.y].panOver(true);
		}
		ui.update();
	}
	
	public void exit(int x, int y){
		for(Point i : bs.getAdjTiles(x, y, board)){
			board[i.x][i.y].panOver(false);
		}
		ui.update();
	}
	
	public Tile[][] getBoard(){
		return board;
	}
	
	public void gameEnd(){
		for(Tile[] i : board){
			for(Tile j : i){
				j.isVisible(true);
				j.isFlag(false);
			}
		}
		ui.youLoose();
	}
	
	public boolean isWin(){
		for(Tile[] i : board){
			for(Tile j : i){
				if(!j.isMine() && !j.isVisible()) return false;
				if(j.isMine() && j.isVisible()) return false;
			}
		}
		ui.youWin();
		return true;
	}
	
	public void newGame(){
		isFirstClick = true;
		genBoard();
		ui.updatePanel();
	}
	
	public void customOptions(){
		Integer[] values = ui.customOptions();
		if(values != null){
			info.custom(values[0], values[1], values[2]);
			infoChange();
		}
	}
	
	public void infoChange(){
		ui.infoChangeDialog();
	}

}
