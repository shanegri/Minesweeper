package ms.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import ms.generator.Tile;

public class BoundsSelector {
	
	public HashSet<Point> pointCascade(int startPx, int startPy, Tile[][] b){
		HashSet<Point> newC= new HashSet<Point>();
		HashSet<Point> toCheck = new HashSet<Point>();
		HashSet<Point> rv = new HashSet<Point>();
		toCheck.add(new Point(startPx, startPy));
		while(!toCheck.isEmpty()){
			for(Point i : toCheck){
				for(Point j : singlePointCascade(i.x, i.y, b)){
					if(!rv.contains(j) && !toCheck.contains(j)){
						newC.add(j);
					}
				}
			}
			rv.addAll(toCheck);
			HashSet<Point> tmp = toCheck;;
			toCheck = newC;
			newC = tmp;
			newC.clear();
		}
		return rv;
	}
	
	public HashSet<Point> tileExpand(int startPx, int startPy, Tile[][] b){
		HashSet<Point> rv = pointCascade(startPx, startPy, b);
		HashSet<Point> pointsToAdd = new HashSet<Point>();
		int x1, y1;
		for(Point i : rv){
			if(b[i.x][i.y].getSurMines() == 0){
			x1 = i.x + 1; y1 = i.y - 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x + 1; y1 = i.y    ; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x + 1; y1 = i.y + 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x    ; y1 = i.y - 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x    ; y1 = i.y + 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x - 1; y1 = i.y - 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x - 1; y1 = i.y    ; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));
			x1 = i.x - 1; y1 = i.y + 1; if(tileExpandLogic(x1, y1 ,b)) pointsToAdd.add(new Point(x1, y1));		
			}
		}
		rv.addAll(pointsToAdd);
		return rv;
	}
	
	public boolean tileExpandLogic(int x1, int y1, Tile[][] b){
		return isInBnds(x1, y1, b.length, b[0].length) && !b[x1][y1].isMine() && b[x1][y1].getSurMines() != 0 ;
	}
	
	public ArrayList<Point> singlePointCascade(int x, int y, Tile[][] b){
		ArrayList<Point> retval = new ArrayList<Point>();
		int x1, y1;
		x1 = x + 1; y1 = y; 	if(isFreeSpace(x1, y1, b)) retval.add(new Point(x1, y1));
		x1 = x; y1 = y - 1; 	if(isFreeSpace(x1, y1, b)) retval.add(new Point(x1, y1));
		x1 = x; y1 = y + 1; 	if(isFreeSpace(x1, y1, b)) retval.add(new Point(x1, y1));
		x1 = x - 1; y1 = y; 	if(isFreeSpace(x1, y1, b)) retval.add(new Point(x1, y1));
		return retval;
	}
	
	public boolean isFreeSpace(int x, int y, Tile[][] b){
		boolean retval = true;
		if((x < 0) || (x >= b.length) || (y < 0) || (y >= b[0].length)) {
			retval = false;
		} else {
			if(b[x][y].getSurMines() != 0) retval = false;  
			if(b[x][y].isMine()) retval = false;  
		}
		return retval;
	}
	
	public boolean isInBnds(int x, int y, int xS, int yS){
		boolean retVal = true;
		if(x >= xS || x < 0) retVal = false;
		if(y >= yS || y < 0) retVal = false;
		return retVal;
	}
	
	public HashSet<Point> getAdjTiles(int x, int y, Tile[][] b){
		HashSet<Point> retVal = new HashSet<Point>();
		retVal.add(new Point(x, y));
		int x1, y1;
		x1 = x + 1; y1 = y - 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x + 1; y1 = y    ; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x + 1; y1 = y + 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x    ; y1 = y - 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x    ; y1 = y + 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x - 1; y1 = y - 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x - 1; y1 = y    ; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		x1 = x - 1; y1 = y + 1; if(isInBnds(x1, y1, b.length, b[0].length)) retVal.add(new Point(x1, y1));
		return retVal;
	}

}
