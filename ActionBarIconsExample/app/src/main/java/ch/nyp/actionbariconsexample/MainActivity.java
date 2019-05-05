package ch.nyp.actionbariconsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Hauptactivity der App. Wird beim App-Start angezeigt. <br/>
 * Diese Activity verfügt über ein ActionBar-Menü mit folgenden Einträgen:<br/>
 * - Icon Activity 2: Öffnet das {@link Activity2} <br/>
 * - Icon Activity 3: Öffnet das {@link Activity3} <br/></br>
 *
 * History:
 * <PRE>
 * 1.0	09.05.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert. Das XML-File wird dem Activity zugeordnet.
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

	/**
	 * Erstellt das ActionBar-Menü aus dem XML-File menu/menu_main.xml.
	 *
	 * @param menu Das ActionBar-Menü, in welchem die Menü-Einträge aus dem XML-File angezeigt
	 * werden sollen.
	 *
	 * @return true, damit ActionBar-Menü angezeigt wird.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Menü inflaten (XML). Fügt in der ActionBar die Menüeinträge hinzu.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	/**
	 * Methode wird aufgerufen, sobald auf ein Menü-Item in der ActionBar geklickt wird.
	 * Je nach geklicktem Menü-Item wird die korrekte Aktion ausgeführt.
	 *
	 * @param item Geklicktes Menü-Item.
	 * @return boolean Return false to allow normal menu processing to
	 *         proceed, true to consume it here.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int menuItemId = item.getItemId();

		if (menuItemId == R.id.action_start_activity2) {
			//Menü-Eintrag mit Icon wurde geklickt
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Activity2.class);
			startActivity(intent);
		} else if (menuItemId == R.id.action_start_activity3) {
			//Menü-Eintrag "Activity 3" wurde geklickt.
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Activity3.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}
}
