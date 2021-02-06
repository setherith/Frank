package common;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = -5758599204265916914L;

	public Frame() {
		try {
			InputStream is = getClass().getResourceAsStream("/logo.png");
			setIconImage(ImageIO.read(is));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
