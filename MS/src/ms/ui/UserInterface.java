package ms.ui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ms.generator.Tile;
import ms.info.GameInfo;
import ms.logic.Logic;

public class UserInterface {
		
		private Logic l;
		private JFrame window;
		private JPanel panel;
		private JButton[][] bnts;
		private GameInfo info;
		private ImageReader ir;
	
	
	public UserInterface(){
		ir = new ImageReader();
		info = GameInfo.getInstance();
		l = new Logic(this);
		window = new JFrame("MineSweeper");
		panel = new JPanel();
		
		MenuHandler mh = new MenuHandler(l);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
			JMenuItem fileNewGame = new JMenuItem("New Game");
			fileNewGame.setActionCommand("New Game");
			fileNewGame.addActionListener(mh);
			JMenuItem fileExit = new JMenuItem("Exit");
			fileExit.setActionCommand("Exit");
			fileExit.addActionListener(mh);
			
			file.add(fileNewGame);
			file.add(fileExit);
		
		
		JMenu settings = new JMenu("Settings");
			JMenuItem settingsBeginner = new JMenuItem("Beginner");
			settingsBeginner.setActionCommand("Beginner");
			settingsBeginner.addActionListener(mh);
			JMenuItem settingsIntermediate = new JMenuItem("Intermediate");
			settingsIntermediate.setActionCommand("Intermediate");
			settingsIntermediate.addActionListener(mh);
			JMenuItem settingsExpert = new JMenuItem("Expert");
			settingsExpert.setActionCommand("Expert");
			settingsExpert.addActionListener(mh);
			JMenuItem settingsCustom = new JMenuItem("Custom");
			settingsCustom.setActionCommand("Custom");
			settingsCustom.addActionListener(mh);
	
		
			settings.add(settingsBeginner);
			settings.add(settingsIntermediate);
			settings.add(settingsExpert);
			settings.add(settingsCustom);
		
		
		menuBar.add(file);
		menuBar.add(settings);
		
			
		window.setJMenuBar(menuBar);	
		updatePanel();
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	

	public JButton createButton(int x, int y){
		JButton retVal = new JButton();
		retVal.addMouseListener(new ButtonHandler(l, x, y));
		bnts[x][y] = retVal;
		return retVal;
	}
	
	public void panelSetUp(int x, int y){
		bnts = new JButton[x][y];
		panel.removeAll();
		panel.setLayout(new GridLayout(x, y));
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				panel.add(createButton(i, j));
			}
		}	
		window.setSize(info.getYSize() * 40, info.getXSize() * 40);
		window.revalidate();
	}
	
	public void update(){
		Tile[][] b = l.getBoard();
		for(int i = 0; i < bnts.length; i++){
			for(int j = 0; j < bnts[0].length; j++){
				if(b[i][j].panOver()){			
				} else {
					if(b[i][j].isFlag()){
						bnts[i][j].setIcon(new ImageIcon(ir.getImage()[8]));
					} else if (b[i][j].isVisible()){
						bnts[i][j].setBackground(Color.white);
						if(b[i][j].isMine()){
							bnts[i][j].setIcon(new ImageIcon(ir.getImage()[9]));	
						} else{		
							if(b[i][j].getSurMines() != 0){
								bnts[i][j].setIcon(new ImageIcon(ir.getImage()[b[i][j].getSurMines() - 1]));
							} else {
								bnts[i][j].setIcon(null);
							}			
						}
					} else if (!b[i][j].isVisible()){
						bnts[i][j].setIcon(null);
					}
				} 
		}
	}
		
		
	}
	
	public Integer[] customOptions(){
		Integer[] retVal = new Integer[3];
		JTextField mineAmt = new JTextField(5);
	    JTextField xSize = new JTextField(5);
	    JTextField ySize = new JTextField(5);

	    JPanel optionPanel = new JPanel();
	    optionPanel.add(new JLabel("# Mines"));
	    optionPanel.add(mineAmt);
	    optionPanel.add(Box.createHorizontalStrut(5)); 
	    optionPanel.add(new JLabel("X Size"));
	    optionPanel.add(xSize);
	    optionPanel.add(Box.createHorizontalStrut(5)); 
	    optionPanel.add(new JLabel("Y Size"));
	    optionPanel.add(ySize);
	    optionPanel.setLayout(new GridLayout(3, 2));

	    int result = JOptionPane.showConfirmDialog(null, optionPanel, 
	               "Please Enter New Values", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	    	try{
		    	retVal[0] = Integer.valueOf(mineAmt.getText());
		    	retVal[1] = Integer.valueOf(xSize.getText());
		    	retVal[2] = Integer.valueOf(ySize.getText());
		    	if((retVal[0] > retVal[1] * retVal[2])){
		    		JOptionPane.showMessageDialog(window, "Invalid Input");
		    		return null;
		    	}
	    	} catch (NumberFormatException e){
	    		JOptionPane.showMessageDialog(window, "Invalid Input");
	    		return null;
	    	}
	    } else {
	    	return null;
	    }
	    
		return retVal;
	}
	
	public void infoChangeDialog(){
		JOptionPane.showMessageDialog(window, "Select New Game or finish your current game");

	}
	
	public void updatePanel(){
		panelSetUp(info.getXSize(), info.getYSize());
		update();
	}
	
	public void youWin(){
		JOptionPane.showMessageDialog(window, "You Win!!");
	}
	
	public void youLoose(){
		JOptionPane.showMessageDialog(window, "You Loose :(");

	}

	
	
}
