package ch.nyp.staticspinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Einstiegsactivity.
 * Beinhaltet einen Spinner mit statischen Einträgen
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * Wird beim Starten des Activity ausgeführt.
	 * Verbindet den JavaCode mit dem XML und initialisiert die Instanzvariablen.
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * 							 gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Spinner placesSpinner = findViewById(R.id.spinner_main_places);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.main_spinner_places, android.R.layout.simple_spinner_item);
		placesSpinner.setAdapter(adapter);
	}
}
