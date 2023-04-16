package piano;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resources {

	public static ImageIcon songPlayer;
	public static ImageIcon stop;
	public static Font font;

	static {
		try {
			Image img = ImageIO.read(Resources.class.getResourceAsStream("/navi.png")).getScaledInstance(16, 16,
					Image.SCALE_SMOOTH);
			songPlayer = new ImageIcon(img);
			
			img = ImageIO.read(Resources.class.getResourceAsStream("/stop.png")).getScaledInstance(16, 16,
					Image.SCALE_SMOOTH);
			stop = new ImageIcon(img);
			
			font = Font.createFont(Font.PLAIN, Resources.class.getResourceAsStream("/SF-UI-Display-Regular.otf"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
