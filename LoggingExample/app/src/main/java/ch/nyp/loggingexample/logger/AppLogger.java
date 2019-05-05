package ch.nyp.loggingexample.logger;

import android.os.Environment;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Klasse, welche die Möglichkeit bietet, Meldungen und Exceptions zu loggen.
 * Jede Meldung und Exception wird einerseits in ein Logfile (loggingDemo.log),
 * andererseits mit Logcat, geloggt.
 *
 * Das Logfile wird auf dem Gerät entweder im Root-Verzeichnis des internen Speichers oder im
 * Root-Verzeichnis des externen Speichers (sofern vorhanden, z.B. Speicherkarte), abgelegt.
 *
 * Das Logfile darf eine maximale Grösse von 10MB annehmen.
 *
 * Diese Klasse wurde nach dem Singleton-Pattern realisiert. Nur eine Instanz der Klasse kann
 * existieren.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class AppLogger {

	private static final String LOG_FILE_PATH = "/loggingDemo.log";
	//Maximal Grösse des Logfiles: 10MB
	private static final int LOG_FILE_MAX_SIZE_BYTES =  10485760;

	private static AppLogger sInstance = null;
	private String mLogFilePath;
	private LoggingFormatter mLoggingFormatter;

	/**
	 * Konstruktor. Initialisiert den Pfad des Logfiles und setzt mit Hilfe der Klasse {@link
	 * LoggingFormatter} den Aufbau einer Zeile im Logfile.
	 *
	 * @since 1.0
	 */
	private AppLogger() {
		if (Environment.getExternalStorageState().compareTo(Environment.MEDIA_MOUNTED) == 0) {
			mLogFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			mLogFilePath = Environment.getDataDirectory().getAbsolutePath();
		}
		mLogFilePath += LOG_FILE_PATH;
		mLoggingFormatter = new LoggingFormatter();
	}

	/**
	 * Gibt die aktuelle Instanz der Klasse zurück. Falls noch keine Instanz existiert,
	 * wird eine neue Instanz erzeugt und diese zurückgegeben.
	 * Singleton-Pattern.
	 *
	 * @return Aktuelle Instanz der Klasse.
	 * @since 1.0
	 */
	public static AppLogger getInstance() {
		if(sInstance == null) {
			sInstance = new AppLogger();
		}
		return sInstance;
	}

	/**
	 * Loggt eine Nachricht.
	 * Das Muster zum loggen einer Nachricht ist in der Klasse {@link LoggingFormatter} definiert.
	 *
	 * @param logLevel Level der zu loggenden Nachricht. z.B. INFO für normale Nachrichten,
	 * WARNING für Fehler.
	 * @param loggerName Name des Loggers. Hier soll die Klasse und die Methode angegeben werden,
	 * in welchen diese Methode aufgerufen wird. z.B: XelHaApplication.onCreate.
	 * @param message Zu loggende Nachricht.
	 * @since 1.0
	 */
	public void logMessage(Level logLevel, String loggerName, String message) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(mLogFilePath, LOG_FILE_MAX_SIZE_BYTES, 1, true);
			fileHandler.setFormatter(new SimpleFormatter());
			LogRecord logRecord = new LogRecord(logLevel, message);
			logRecord.setLoggerName(loggerName);
			fileHandler.setFormatter(mLoggingFormatter);
			fileHandler.publish(logRecord);
			fileHandler.flush();

			if (logLevel.equals(Level.INFO)) {
				Log.i(loggerName, message);
			} else if (logLevel.equals(Level.WARNING)) {
				Log.e(loggerName, message);
			}
		} catch (Exception e){
			//Nichts tun
		}finally{
			if (fileHandler != null)
				fileHandler.close();
		}
	}

	/**
	 * Loggt eine Exception. Beim Loggen der Exception wird der übergebenen Nachricht (message)
	 * noch die Exception-Message, sowie der Exception-Stacktrace angehängt.
	 *
	 * @param loggerName Name des Loggers. Hier soll die Klasse und die Methode angegeben werden,
	 * in welchen diese Methode aufgerufen wird. z.B: XelHaApplication.onCreate.
	 * @param message Zu loggende Nachricht.
	 * @param exception Zu loggende Exception.
	 * @since 1.0
	 */
	public void logException(String loggerName, String message,
							 Exception exception) {
		String logMessage = message + " Exception-Message: " + exception.getMessage() +
				" Exception-Stacktrace: " + ExceptionUtils.getStackTrace(exception);
		logMessage(Level.WARNING, loggerName, logMessage);
	}
}
