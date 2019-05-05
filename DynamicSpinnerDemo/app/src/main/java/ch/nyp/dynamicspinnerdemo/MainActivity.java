package ch.nyp.dynamicspinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ch.nyp.dynamicspinnerdemo.model.User;

/**
 * Einstiegsactivity.
 * Beinhaltet einen Spinner mit dynamischen Einträgen
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
	 * Erstellt einen Spinner mit 3 Lernenden.
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * 							 gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Spinner placesSpinner = findViewById(R.id.spinner_main_lernende);

		User lernender1 = new User();
		lernender1.setId(1);
		lernender1.setFirstName("Severin");
		lernender1.setLastName("Gafner");
		User lernender2 = new User();
		lernender2.setId(2);
		lernender2.setFirstName("Jean-Michel");
		lernender2.setLastName("Carrel");
		User lernender3 = new User();
		lernender3.setId(1);
		lernender3.setFirstName("Nathanael");
		lernender3.setLastName("Hunziker");

		List<User> lernendeToAdd = new ArrayList<>();
		lernendeToAdd.add(lernender1);
		lernendeToAdd.add(lernender2);
		lernendeToAdd.add(lernender3);

		//Adapter erstellen und die Lernenden hinzufügen
		LernendeAdapter lernendeAdapter = new LernendeAdapter(this, lernendeToAdd);

		//Adapter  dem Spinner hinzufügen
		placesSpinner.setAdapter(lernendeAdapter);
	}
}
