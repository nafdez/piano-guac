package piano;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

import player.Note;
import player.Player;

public class Keys extends JLayeredPane implements MouseListener {

	private static final long serialVersionUID = 1L;
	ArrayList<JButton> keys = new ArrayList<>();
	int nKeys;

	public Keys(int nKeys) {
		this.nKeys = nKeys;
		inicialize(Player.getNotes());
	}

	private void inicialize(List<String> notes) {
		setLayout(null);
		int xPos = 0;
		int yPos = 0;

		for (int i = 0; i < nKeys; i++) {
			for (String note : notes) {
				keys.add(newButton((i + 1) + note, null));
			}

			JButton currentKey = keys.get(i);

			if (currentKey.getActionCommand().contains("#")) {
				styleButton(currentKey, Color.WHITE, Color.BLACK, (xPos - 12), yPos, 25, 150);
				currentKey.setFont(Resources.font.deriveFont(10f));
				add(currentKey, Integer.valueOf(1));
			} else {
				styleButton(currentKey, Color.BLACK, Color.WHITE, xPos, yPos, 37, 210);
				xPos += 37;
				currentKey.setFont(Resources.font.deriveFont(14f));
				add(currentKey, Integer.valueOf(0));
			}

		}

	}

	private void styleButton(JButton key, Color textColor, Color bgColor, int xStart, int yStart, int width,
			int height) {
		key.setForeground(textColor);
		key.setBackground(bgColor);
		key.setBounds(xStart, yStart, width, height);

		key.setMargin(new Insets(0, 0, 0, 0));
		key.setFocusable(false);
		key.setVerticalAlignment(JButton.BOTTOM);
	}

	private JButton newButton(String actionCommand, ImageIcon icon) {
		JButton button = new JButton(icon);
		button.setName(actionCommand);
		button.setText(actionCommand);
		button.addMouseListener(this);
		return button;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		new Thread(new Note(e.getActionCommand(), 300)).start();
////			new Thread(player::playSongOfTime).start();
////			player.play(e.getActionCommand(), 300);
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		long t1 = System.nanoTime();
		// do some stuff
		new Thread(new Note(e.getComponent().getName(), t1)).start();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Note.stop();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
