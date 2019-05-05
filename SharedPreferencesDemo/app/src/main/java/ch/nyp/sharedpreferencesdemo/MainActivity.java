package ch.nyp.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Hauptactivity der App. Wird beim App-Start angezeigt. <br/>
 * Dieses Activity beinhaltet einen Button, welcher einen Vor- und Nachnamen in die Shared
 * Preferences schreibt und einen zweiten Button, welcher die Shared Preferences wieder ausliest
 * und die Daten in einem Toast ausgibt.
 *
 * History:
 * <PRE>
 * 1.0	31.01.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Beinhaltet zudem die anonymen OnClickListener zum Schreiben und Lesen in und aus den
	 * Shared Preferences.
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

		Button buttonWrite = findViewById(R.id.button_main_write);
		buttonWrite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
						"demo_preferences", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putString("KEY_VORNAME", "Fritz");
				editor.putString("KEY_NACHNAME", "Müller");
				editor.commit();
			}
		});

		Button buttonRead = findViewById(R.id.button_main_read);
		buttonRead.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
						"demo_preferences", Context.MODE_PRIVATE);
				String firstName = sharedPref.getString("KEY_VORNAME", "");
				String lastName = sharedPref.getString("KEY_NACHNAME", "");

				String message = "vorname: " + firstName + "\n" + "nachname: " + lastName;
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
			}
		});
	}
}
