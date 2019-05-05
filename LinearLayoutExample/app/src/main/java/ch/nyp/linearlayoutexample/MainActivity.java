package ch.nyp.linearlayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Hauptactivity der App. Wird beim App-Start angezeigt. <br/>
 * Diese Activity zeigt in einem LinearLayout folgende Felder an:<br/>
 * - Herzlich Willkommen Meldung <br/>
 * - Vorname + Eingabefeld <br/>
 * - Nachname + Eingabefeld <br/>
 * - 2 Buttons <br/><br/>
 *
 * History:
 * <PRE>
 * 1.0	22.01.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert, d.h. das XML-File mit der Activity verbunden.
	 *
	 * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
	 * 							 being shut down then this Bundle contains the data it most
	 * 							 recently supplied in onSaveInstanceState(Bundle). Note: Otherwise
	 * 							 it is null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
