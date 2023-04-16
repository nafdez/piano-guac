package piano;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;

	Launcher(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setPreferredSize(new Dimension(750, 285));
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		Player player;
		try {
			player = new Player();
			
			Keys keys = new Keys(61, player);
			keys.scrollRectToVisible(getBounds());
			JScrollPane scrollKeyPane = new JScrollPane(keys);
			
			Menu menu = new Menu(player);
			
			menu.setMaximumSize(new Dimension(750, 16));
			
			contentPane.add(scrollKeyPane);
			contentPane.add(menu);

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
