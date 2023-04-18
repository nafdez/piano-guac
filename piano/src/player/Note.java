package player;

public class Note implements Runnable {

	String note;
	int duration;

	public Note(String note, int duration) {
		this.note = note;
		this.duration = duration;
	}

	public void play() {
		// * start playing a note
		if (note != null)
			Player.getChannels()[Player.getInstrument()].noteOn(Player.id(note), Player.getVolume());
		// * wait
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
		// * stop playing a note
		if (note != null)
			Player.getChannels()[Player.getInstrument()].noteOff(Player.id(note));
	}

	@Override
	public void run() {
		this.play();
	}
}
