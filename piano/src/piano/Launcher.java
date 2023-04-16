package piano;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;

	Launcher(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setPreferredSize(new Dimension(750, 250));
		
		Container contentPane = getContentPane();

		Player player;
		try {
			player = new Player();
			Keys keys = new Keys(61, player);
			contentPane.add(keys);

			pack();
			setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new Launcher(args).setVisible(true));
	}

}
