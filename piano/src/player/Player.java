package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * A little example showing how to play a tune in Java.
 * 
 * Inputs are not sanitized or checked, this is just to show how simple it is.
 * 
 * @author Peter
 * 
 *         Modified to use with this application
 */
public class Player {

	private static Synthesizer synth;
	private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private static MidiChannel[] channels;
	private static int INSTRUMENT = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private static int VOLUME = 127; // between 0 et 127

	public Player() throws Exception {
		// * Open a synthesizer
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		channels = synth.getChannels();
	}

	public static List<String> getNotes() {
		return Collections.unmodifiableList(notes);
	}

	public int getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(int VOLUME) {
		Player.VOLUME = VOLUME;
	}

	public static MidiChannel[] getChannels() {
		return channels;
	}

	public static int getInstrument() {
		return INSTRUMENT;
	}

	public static int getVolume() {
		return VOLUME;
	}

	/**
	 * Plays the given note for the given duration
	 */
	public void play(String note, int duration) throws InterruptedException {
		// * start playing a note
		channels[INSTRUMENT].noteOn(id(note), VOLUME);
		// * wait
		Thread.sleep(duration);
		// * stop playing a note
		channels[INSTRUMENT].noteOff(id(note));
	}

	/**
	 * Returns the MIDI id for a given note: eg. 4C -> 60
	 * 
	 * @return
	 */
	public static int id(String note) {
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12;
	}

	public void close() {
		synth.close();
	}

	static final List<Note> songOfTime;
	static {
		songOfTime = new ArrayList<>();
		songOfTime.add(new Note("6A", 300));
		songOfTime.add(new Note("6D", 500));
		songOfTime.add(new Note(null, 500));
		songOfTime.add(new Note("6F", 500));
		songOfTime.add(new Note("6A", 500));
		songOfTime.add(new Note("6D", 500));
		songOfTime.add(new Note(null, 500));
		songOfTime.add(new Note("6F", 300));
		songOfTime.add(new Note("6A", 300));
		songOfTime.add(new Note("7C", 300));
		songOfTime.add(new Note("6B", 500));
		songOfTime.add(new Note("6G", 500));
		songOfTime.add(new Note("6F", 300));
		songOfTime.add(new Note("6G", 300));
		songOfTime.add(new Note("6A", 500));
		songOfTime.add(new Note("6D", 500));
		songOfTime.add(new Note("6C", 300));
		songOfTime.add(new Note("6E", 300));
		songOfTime.add(new Note("6D", 600));
	}

	static final List<Note> laResaka;
	static {
		laResaka = new ArrayList<>();
		laResaka.add(new Note("3E", 300));
		laResaka.add(new Note("3A", 300));
		for (int i = 0; i < 4; i++) {
			laResaka.add(new Note("3E", 150));
		}
		laResaka.add(new Note("3A", 150));
		laResaka.add(new Note("3E", 150));
		laResaka.add(new Note("4C", 150));
		laResaka.add(new Note("4C", 150));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 500));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 200));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 200));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 300));

		// 2
		for (int i = 0; i < 6; i++) {
			laResaka.add(new Note("3E", 150));
		}
		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 500));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 200));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("3B", 200));

		laResaka.add(new Note("4F", 175));
		laResaka.add(new Note("4E", 200));
		laResaka.add(new Note("4C", 200));
		laResaka.add(new Note("3B", 200));
		laResaka.add(new Note("3A", 300));
	}

	private volatile boolean stop;

	public void playSongOfTime() {
		stop = false;
		Iterator<Note> i = songOfTime.iterator();
		while (!stop && i.hasNext()) {
			i.next().play();
		}
	}

	public void playLaResaka() {
		stop = false;
		Iterator<Note> i = laResaka.iterator();
		while (!stop && i.hasNext()) {
			i.next().play();
		}
	}

	public void playNote(String note, int duration) {
		new Note(note, duration).play();
	}

	public void stop() {
		stop = true;
	}

}
