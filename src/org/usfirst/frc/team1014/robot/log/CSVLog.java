package org.usfirst.frc.team1014.robot.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVLog {

	private static final String DELIMITER = ",";
	private boolean entryState;
	private BufferedWriter out;
	
	private static Logger logger = Logger.getLogger(CSVLog.class.getName());

	List<CSVLogColumn> columns;
	private final String tablePath;

	public CSVLog(String logPath, String tablePath) {
		this.tablePath = tablePath;
		entryState = true;
		try {
			out = new BufferedWriter(new FileWriter(logPath, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		columns = new ArrayList<>();
	}
	
	public void add(CSVLogColumn column) {
		if(!entryState)
			logger.warning("Attempt to add CSVLogColumn outside of entry mode");
		columns.add(column);
	}
	
	public void log() {
		List<String> data = new ArrayList<>();
		columns.stream().forEachOrdered((c) -> data.add(c.get()));
		String[] tmp = new String[data.size()];
		for(int i = 0; i < tmp.length; i++)
			tmp[i] = data.get(i);
		writeLine(String.join(DELIMITER, tmp));
		flush();
	}

	public void done() {
		if(!entryState)
			logger.warning("Call to done outside of entry mode");
		entryState = false;

		// write headers
		List<String> names = new ArrayList<>();
		columns.stream().forEachOrdered((c) -> names.add(c.getName()));
		String[] tmp = new String[names.size()];
		for(int i = 0; i < tmp.length; i++)
			tmp[i] = names.get(i);
		writeLine(String.join(DELIMITER, tmp));
		
		// write table
		try {
			BufferedWriter tableOut = new BufferedWriter(new FileWriter(tablePath));
			tableOut.write("name, type, unit, description\n");
			columns.stream().forEachOrdered((c) -> {
				try {
					tableOut.write(c.getName() + ", " + c.getType() + ", \"" + c.getUnit() + "\", \"" + c.getDescription() + "\"" + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			tableOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void flush() {
		try {
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLine(String string) {
		try {
			out.write(string + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
