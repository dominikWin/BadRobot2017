package org.usfirst.frc.team1014.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RobotSessionLog {
	private static final String LOG_ROOT = "/home/lvuser/log";
	private static final String LOG_SESSION_TABLE = LOG_ROOT + "/sessions";
	private static final String SESSION_TABLE_DATE_FORMAT = "MMMM dd, yyyy hh:mm:ss a"; // matches
																						// logger
	private static final int SESSION_ID_LENGTH = 3;
	private static final String SESSION_ID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static Optional<RobotSessionLog> instance = Optional.empty();

	private final String sessionID;
	private final String sessionDirPath;
	private Logger rootLogger = Logger.getLogger("");
	private Logger logger = Logger.getLogger(RobotSessionLog.class.getName());

	public static Optional<RobotSessionLog> createRobotSessionLog() {
		if (instance.isPresent()) {
			System.err.println("Failed to create second instance of RobotSessionLog");
			return Optional.empty();
		}

		final File rootDir = new File(LOG_ROOT);
		if (!rootDir.exists()) {
			if (!rootDir.mkdirs()) {
				System.err.println("Failed to create non-existant directory " + rootDir.getAbsolutePath());
				return Optional.empty();
			}
		}

		if (rootDir.isFile()) {
			System.err.println(rootDir.getAbsolutePath() + " is a file, not a directory");
			return Optional.empty();
		}

		String sessionID;
		do {
			sessionID = genPossibleSessionID();
		} while (new File(LOG_ROOT + "/" + sessionID).exists());

		File sessionDir = new File(LOG_ROOT + "/" + sessionID);
		if (!sessionDir.mkdirs()) {
			System.err.println("Can't create directory " + sessionDir.getAbsolutePath());
			return Optional.empty();
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(LOG_SESSION_TABLE, true));
			out.write((new SimpleDateFormat(SESSION_TABLE_DATE_FORMAT)).format(new Date()) + ": " + sessionID + "\n");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		instance = Optional.of(new RobotSessionLog(sessionID));
		return instance;
	}

	private static String genPossibleSessionID() {
		String out = "";

		for (int i = 0; i < SESSION_ID_LENGTH; i++) {
			char possibleChar;

			do {
				possibleChar = SESSION_ID_CHARS.charAt((int) (Math.random() * (double) SESSION_ID_CHARS.length()));
			} while (out.contains(Character.toString(possibleChar)));

			out += possibleChar;
		}

		return out;
	}

	private RobotSessionLog(String sessionID) {
		this.sessionID = sessionID;
		this.sessionDirPath = LOG_ROOT + "/" + sessionID;

		// remove default console logger, stop double printing
		for (Handler h : rootLogger.getHandlers())
			rootLogger.removeHandler(h);

		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(sessionDirPath + "/run.log");
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		rootLogger.addHandler(fileHandler);
		fileHandler.setFormatter(new SimpleFormatter());
		fileHandler.setLevel(Level.ALL);
		try {
			fileHandler.setEncoding("UTF8");
		} catch (Exception e) {
			// if the encoding is wrong crash, this should never happen
			e.printStackTrace();
			System.exit(1);
		}
		ConsoleHandler consoleHandler = new ConsoleHandler();
		rootLogger.addHandler(consoleHandler);
		consoleHandler.setLevel(Level.INFO);
	}

	public String getSessionID() {
		return sessionID;
	}
}
