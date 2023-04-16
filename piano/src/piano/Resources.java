package piano;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resources {

	public static ImageIcon songPlayer;
	public static Font font;

	static {
		try {
			Image img = ImageIO.read(Resources.class.getResourceAsStream("/navi.png")).getScaledInstance(16, 16,
					Image.SCALE_SMOOTH);
			songPlayer = new ImageIcon(img);
			
			font = Font.createFont(Font.PLAIN, Resources.class.getResourceAsStream("/SF-UI-Display-Regular.otf"))
					.deriveFont(14f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
