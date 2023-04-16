package piano;

import java.util.Arrays;
import java.util.Collections;
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
 * Modified to use with this application
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

	public List<String> getNotes() {
		return Collections.unmodifiableList(notes);
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
	 * Plays nothing for the given duration
	 */
	private void rest(int duration) throws InterruptedException {
		Thread.sleep(duration);
	}

	/**
	 * Returns the MIDI id for a given note: eg. 4C -> 60
	 * 
	 * @return
	 */
	private static int id(String note) {
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12;
	}
	
	public void close() {
		synth.close();
	}

	public void songOfTime() throws InterruptedException {
		// Song of time
		play("6A", 300);
		play("6D", 500);
		rest(500);
		play("6F", 500);
		play("6A", 500);
		play("6D", 500);
		rest(500);
		play("6F", 300);
		play("6A", 300);
		play("7C", 300);
		play("6B", 500);
		play("6G", 500);
		play("6F", 300);
		play("6G", 300);
		play("6A", 500);
		play("6D", 500);
		play("6C", 300);
		play("6E", 300);
		play("6D", 600);
	}
	
}
