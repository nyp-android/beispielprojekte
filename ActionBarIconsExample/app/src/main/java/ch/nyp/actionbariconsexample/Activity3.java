package ch.nyp.actionbariconsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity 3 der App. Wird beim Klick auf das Icon in der Action-Bar angezeigt. <br/>
 * Zeigt den Text "Activity 3" an.
 *
 * History:
 * <PRE>
 * 1.0	09.05.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class Activity3 extends AppCompatActivity {

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert. Das XML-File wird dem Activity zugeordnet.<br/>
	 * Setzt zudem den Zurück-Button in der ActionBar. Damit der Zurück-Button funktioniert muss
	 * noch im Manifest das Attribut "parentActivityName" beim jeweiligen Activity gesetzt werden.
	 *
	 *
	 * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
	 * 							 being shut down then this Bundle contains the data it most
	 * 							 recently supplied in onSaveInstanceState(Bundle). Note: Otherwise
	 * 							 it is null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3);

		//Zurück-Button in Action-Bar anzeigen
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
