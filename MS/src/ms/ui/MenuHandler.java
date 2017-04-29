package ms.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ms.info.GameInfo;
import ms.logic.Logic;

public class MenuHandler implements ActionListener {

	Logic l;
	GameInfo info;
	
	public MenuHandler(Logic l){
		this.l = l;
		info = GameInfo.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand())  {
		case "New Game" :
			l.newGame();
			break;
		case "Exit":
			System.exit(0);
			break;
		case "Beginner":
			info.beginner();
			l.infoChange();
			break;
		case "Intermediate":
			info.intermediate();
			l.infoChange();
			break;
		case "Expert":
			info.expert();
			l.infoChange();
			break;
		case "Custom":
			l.customOptions();
			break;
		}
			
			
			
		
	}
	
	

}
