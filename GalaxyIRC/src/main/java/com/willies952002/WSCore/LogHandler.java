package com.willies952002.WSCore;

import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;

/**
 * @author William Surgeon (willies952002)
 * @since 1.0dev
 */
public final class LogHandler {

	Calendar cal = Calendar.getInstance();
	private PrintStream errStream;
	private PrintStream outStream;
	File errFile;
	File outFile;

	public LogHandler(String baseDir) throws Exception {
		String logPath = baseDir + File.separatorChar + "logs"
				+ File.separatorChar;
		String logFileName = "log_"
				+ String.valueOf(cal.get(Calendar.MONTH) + 1)
				+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
				+ String.valueOf(cal.get(Calendar.YEAR) + ".txt");
		File logPathFile = new File(logPath);
		logPathFile.mkdirs();
		errFile = new File(logPath + "err_" + logFileName);
		errFile.createNewFile();
		outFile = new File(logPath + "out_" + logFileName);
		outFile.createNewFile();
		errStream = new PrintStream(errFile);
		outStream = new PrintStream(outFile);
	}

	public void err(String errMsg) {
		errStream.append("[" + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND)
				+ ":" + cal.get(Calendar.MILLISECOND) + "]: " + errMsg);
		errStream.println();
	}

	public void log(String logMsg) {
		outStream.append("[" + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND)
				+ ":" + cal.get(Calendar.MILLISECOND) + "]: " + logMsg);
		outStream.println();
	}

	public void close() {
		if (errStream != null) {
			errStream.close();
		}
		if (outStream != null) {
			outStream.close();
		}
		errStream = null;
		outStream = null;
	}

}
