package ms.ui;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ms.logic.Logic;

public class ButtonHandler implements MouseListener {

	Logic l;
	int x, y;
	boolean lClick, rClick;
	
	public ButtonHandler(Logic l, int x, int y){
		this.l = l;
		this.x = x;
		this.y=  y;

	}
	


	@Override
	public void mouseClicked(MouseEvent e) {

	}



	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1){
			lClick = true; 
		}
		if(e.getButton() == 3) {
			rClick = true; 
		}
		

	}



	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1){
			l.leftButtonClick(x,  y);	
		} else if (e.getButton() == 3){
			l.rightButtonClick(x, y);
		}
		
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		
	}



}
