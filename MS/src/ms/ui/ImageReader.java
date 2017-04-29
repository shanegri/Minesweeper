package ms.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReader {
	
	
	BufferedImage[] images;
	
	public ImageReader(){
		images = new BufferedImage[10];
		try {
			for(int i = 1 ; i < 9 ; i++){
				images[i - 1] = ImageIO.read(new File("images/" + i + ".png"));
			}
			images[8] = ImageIO.read(new File("images/Flag.png"));
			images[9] = ImageIO.read(new File("images/Bomb.png"));
		} catch (IOException e) {
		}
	}
	
	public BufferedImage[] getImage(){
		return images;
	}

}
