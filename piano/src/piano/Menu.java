package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Menu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	Player player;

	public Menu(Player player) {
		this.player = player;
		inicialize();
	}

	private void inicialize() {
		JButton songOfTime = newButton("SoT", Resources.songPlayer);
		songOfTime.setText("");
		add(songOfTime);

		JSlider volume = newSlider("VOLUME", 0, 127);
		volume.getValue();
		Thread volumeListener = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					player.setVOLUME(volume.getValue());
				}
			}
		});
		volumeListener.start();

		add(volume);
	}

	private JButton newButton(String actionCommand, ImageIcon icon) {
		JButton button = new JButton(icon);
		button.setText(actionCommand);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		return button;
	}

	private JSlider newSlider(String actionCommand, int min, int max) {
		JSlider slider = new JSlider();
		slider.setMinimum(min);
		slider.setMaximum(max);
		return slider;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SoT")) {
			System.out.println("hey! Listen!");
			try {
				player.songOfTime();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("VOLUME")) {

		}
	}

}
