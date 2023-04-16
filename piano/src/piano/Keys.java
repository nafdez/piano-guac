package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Keys extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	Player player;
	ArrayList<JButton> keys = new ArrayList<>();
	int nKeys;

	public Keys(int nKeys, Player player) {
		this.player = player;
		this.nKeys = nKeys;
		inicialize(this.player.getNotes());
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
				styleButton(currentKey, Color.WHITE, Color.BLACK, (xPos-25), yPos, 50, 150);
			} else {
				styleButton(currentKey, Color.BLACK, Color.WHITE, xPos, yPos, 75, 250);
				xPos += 75;
			}

			add(currentKey);
		}

	}

	private JButton styleButton(JButton key, Color textColor, Color bgColor, int xStart, int yStart, int width,
			int height) {
		key.setForeground(textColor);
		key.setBackground(bgColor);
		key.setBounds(xStart, yStart, width, height);

		return key;
	}

	private JButton newButton(String actionCommand, ImageIcon icon) {
		JButton button = new JButton(icon);
		button.setText(actionCommand);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			player.play(e.getActionCommand(), 300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
