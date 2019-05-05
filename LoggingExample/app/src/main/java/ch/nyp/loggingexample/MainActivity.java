package ch.nyp.loggingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.logging.Level;

import ch.nyp.loggingexample.logger.AppLogger;

/**
 * Einstiegsactivity.
 * Loggt 10 Info Meldungen nacheinander und 10 WARNING Meldungen.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//10 Info Meldungen loggen
		for (int i = 0; i < 10; i++) {
			AppLogger.getInstance().logMessage(Level.INFO, "MainActivity.onCreate", "Diese " +
					"INFO wird geloggt " + i);
		}

		//10 Warning loggen
		for (int i = 0; i < 10; i++) {
			AppLogger.getInstance().logMessage(Level.WARNING, "MainActivity.onCreate", "Diese " +
					"WARNING wird geloggt " + i);
		}
	}
}
