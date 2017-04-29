package ms.info;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ScoreKeeper {
	
	public ScoreKeeper(){
		BufferedReader br;
		BufferedWriter wr;
		FileReader fr;
		FileWriter fw;
		try{
			fw = new FileWriter(new File("score/Score.txt"));
			fr = new FileReader(new File("score/Score.txt"));
			br = new BufferedReader(fr);
			wr = new BufferedWriter(fw);
			
			
			
		} catch (Exception e){}
		
		
			
	}

}
