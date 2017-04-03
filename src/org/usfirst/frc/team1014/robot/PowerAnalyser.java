package org.usfirst.frc.team1014.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class logs the current draw of individual channels on the PDP. It logs
 * to {@literal /home/lvuser/powerlog-<UNIX TIMESTAMP>.csv}.
 */
public class PowerAnalyser {
	PowerDistributionPanel pdp;
	String fileName = "/home/lvuser/powerlog-" + System.currentTimeMillis() / 1000 + ".csv";
	Optional<FileWriter> fileWriter = Optional.empty();

	/*
	 * The LinkedHashMap is used because it preserves order when used as an
	 * iterator. This makes sure that every time you run through the channels it
	 * prints to the file in the correct order and it matches the order that
	 * each channel was added to the PowerAnalyser.
	 */
	LinkedHashMap<String, Integer> channels;

	public PowerAnalyser(PowerDistributionPanel pdp) {
		channels = new LinkedHashMap<>();
		this.pdp = pdp;
		try {
			fileWriter = Optional.of(new FileWriter(fileName, true));
			System.out.println("Writing power to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds a new channel with a name and channel number (from PDP).
	 * 
	 * @param name
	 * @param channel
	 */
	public void add(String name, int channel) {
		channels.put(name + "-current", channel);
	}

	/**
	 * Prints the headers (names) of each column to the log file. This should
	 * only be called after all the channels are added.
	 */
	public void printFieldHeader() {
		if (!fileWriter.isPresent())
			return;

		String[] vals = getCurrentValuesAsStrings();
		appendStrings(vals);
	}

	/**
	 * Prints a new line to the log and {@link SmartDashboard}.
	 */
	public void update() {
		// Writes current data to SmartDashboard
		channels.forEach((k, v) -> SmartDashboard.putDouble(k, pdp.getCurrent(v)));

		if (!fileWriter.isPresent())
			return;

		String[] vals = getCurrentValuesAsStrings();
		appendStrings(vals);
	}

	/**
	 * Writes the values to the log.
	 */
	private void appendStrings(String[] vals) {
		try {
			fileWriter.get().write(String.join(", ", vals) + "\n");
			fileWriter.get().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return an array of Strings representing the current of each channel, in
	 *         the order used by the channels list
	 */
	private String[] getCurrentValuesAsStrings() {
		List<String> valueStrings = new ArrayList<>();
		channels.forEach((k, v) -> valueStrings.add(k));
		String[] vals = new String[channels.size()];
		valueStrings.toArray(vals);
		return vals;
	}

}
