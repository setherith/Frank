package frank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame {
	
	private static final long serialVersionUID = -2204890755460358552L;
	
	private int size = 256;

	public Splash() {
		setSize(size * 2, size);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		try {
			InputStream is = getClass().getResourceAsStream("/logo.png");
			BufferedImage image = ImageIO.read(is);
			setIconImage(image);
			Image scaled = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); 
			JLabel imagePanel = new JLabel(new ImageIcon(scaled));
			imagePanel.setBounds(128, -10, size, size);
			imagePanel.setVisible(true);
			add(imagePanel);
			is.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(80, 255, 255));
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Arial", Font.BOLD, 21));
		g2.drawString("the renaming tool", 170, size - 30);

		g.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.PLAIN, 12));
		g2.drawString("written by Shane Pudner (2020)", 170, size - 10);
		
	}
}