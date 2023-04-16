package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	Player player;
	JSlider volSlider;
	JLabel volLabel;

	public Menu(Player player) {
		this.player = player;
		inicialize();
	}

	private void inicialize() {
		JButton songOfTime = newButton("SoT", Resources.songPlayer);
		songOfTime.setFocusable(false);
		add(songOfTime);

		JButton stopBtn = newButton("STOP", Resources.stop);
		stopBtn.setFocusable(false);
		add(stopBtn);
		
		volSlider = new JSlider(0, 100, 80);
		volSlider.addChangeListener(this);
		add(volSlider);
		
		volLabel = new JLabel(volSlider.getValue() + "%");
		volLabel.setFont(Resources.font.deriveFont(14f));
		add(volLabel);
	}

	private JButton newButton(String actionCommand, ImageIcon icon) {
		JButton button = new JButton(icon);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Thread stopListener = new Thread(new Runnable() {
			@Override
			public void run() {
				if (e.getActionCommand().equals("STOP")) {
					System.out.println("STOP");
				}
			}
		});

		stopListener.start();

		if (e.getActionCommand().equals("SoT")) {
			System.out.println("Hey! Listen!");
			try {
				player.songOfTime();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		stopListener.interrupt();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		player.setVOLUME(volSlider.getValue());
		volLabel.setText(volSlider.getValue() + "%");
	}

}
