package piano;

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

	public int getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(int VOLUME) {
		this.VOLUME = VOLUME;
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
	
	private volatile boolean stop;
	public void playSongOfTime() {
		stop = false;
		Iterator<Note> i = songOfTime.iterator();
		while (!stop && i.hasNext()) {
			i.next().play();
		}
	}
	
	public void stopSongOfTime() {
		stop = true;
	}
	
	static class Note {
		String note;
		int duration;
		
		public Note(String note, int duration) {
			this.note = note;
			this.duration = duration;
		}

		public void play() {
			// * start playing a note
			if (note != null)
				channels[INSTRUMENT].noteOn(id(note), VOLUME);
			// * wait
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
			}
			// * stop playing a note
			if (note != null)
				channels[INSTRUMENT].noteOff(id(note));
		}
	}
	
}
