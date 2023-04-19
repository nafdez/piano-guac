package player;

public class Note implements Runnable {

	String note;
	int duration;
	volatile static boolean isPressed;
	long t1;

	public Note(String note, int duration) {
		this.note = note;
		this.duration = duration;
	}

	public Note(String note, long t1) {
		this.note = note;
		isPressed = true;
		this.duration = 1;
		this.t1 = t1;
	}

	public void play() {
		System.out.println((System.nanoTime() - t1) / 1e6 + "ms");
		// * start playing a note
		if (note != null)
			Player.getChannels()[Player.getInstrument()].noteOn(Player.id(note), Player.getVolume());
		// * wait
		try {
			if (isPressed)
				while (isPressed)
					Thread.sleep(duration);
			else
				Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
		// * stop playing a note
		if (note != null)
			Player.getChannels()[Player.getInstrument()].noteOff(Player.id(note));
	}

	public static void stop() {
		isPressed = false;
	}

	@Override
	public void run() {
		this.play();
	}
}
