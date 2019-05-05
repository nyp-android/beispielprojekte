package ch.nyp.loggingexample.logger;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Legt fest, wie eine Zeile im Logfile aufgebaut ist.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class LoggingFormatter extends Formatter {

	/**
	 * Legt fest, wie eine Zeile im Logfile aufgebaut ist und erstellt diese Zeile aus dem
	 * übergebenen {@link java.util.logging.LogRecord}-Objekt.
	 *
	 * Eine Zeile ist wie folgt aufgebaut:
	 *
	 * [Datetime] [LogLevel] [LoggerName]: [LogMessage]
	 *
	 * z.B.
	 * 24.04.2015 09:43 INFO LoggingFormat.format: Eine Exception ist aufgetreten.
	 *
	 * @param record Objekt welches geloggt werden soll. Beinhaltet das LogLevel,
	 * den LoggerName und die Nachricht.
	 * @return Zu loggende Zeile.
	 */
	@Override
	public String format(LogRecord record) {
		return new Date(record.getMillis()) + " "
				+ record.getLevel() + " "
				+ record.getLoggerName() + ": "
				+ record.getMessage()
				+ "\n";
	}
}
