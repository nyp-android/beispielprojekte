package ch.nyp.integrationtestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Einstiegsactivity.
 * Diese Activity kann mit dem in diesem Projekt vorhandenen IntegrationTest
 * (MainActivityTest) getestet werden. <br/>
 * Die Activity beinhaltet ein Eingabefeld "Name" und den "Speichern"-Button. Wird auf den
 * Speichern-Button geklickt, so wird zuerst validiert ob der Name gültig ist (>0 und <50
 * Zeichen). Falls der Name gültig ist, wird dieser in einem Toast ausgegeben. <br/><br/>
 *
 * Der IntegrationTest startet den Emulator, gibt gültige/ungültige Namen ein und prüft ob das
 * erwartete Toast ausgegeben wird.
 * <br/><br/>
 *
 * History:
 * 01.02.2018	1.0	Joel Holzer		Klasse erstellt
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	private EditText mEditTextName;
	private Button mButtonSave;

	/**
	 * Wird aufgerufen, sobald auf den Speichern-Button geklickt wird.
	 * Validiert zuerst den eingegeben Text (siehe {@link #validateFormFields()}). Falls der Text
	 * OK ist, wird dieser in einem Toast ausgegeben. Fall NOK, wird eine Fehlermeldung im Toast
	 * ausgegeben.
	 */
	private View.OnClickListener mSaveOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View sendButton) {
			//Eingegebener Text in einem Toast, welches 3.5 Sekunden (Toast.LENGTH_LONG) angezeigt
			// wird, ausgeben
			String name = mEditTextName.getText().toString();

			String errorMessage = validateFormFields();
			String toastMessage;
			if (errorMessage == null) {
				//Kein Validierungsfehler aufgetreten
				toastMessage = name;
			} else {
				toastMessage = errorMessage;
			}
			Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
			toast.show();

		}
	};

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert.
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

		mEditTextName = findViewById(R.id.edittext_main_name);
		mButtonSave = findViewById(R.id.button_main_save);

		mButtonSave.setOnClickListener(mSaveOnClickListener);
	}

	/**
	 * Validiert den im Textfeld eingegeben Name. Der Name ist NOK wenn: <br/>
	 * - leer <br/>
	 * - länger als 50 Zeichen <br/><br/>
	 *
	 * Wenn der Name nicht OK ist wird die entsprechende Fehlermeldung zurückgegeben. Wenn der
	 * Name ok ist wird null zurückgegeben.
	 *
	 * @return Fehlermeldung, wenn Name NOK ist, null wenn Name ok ist.
	 */
	public String validateFormFields() {
		String name = mEditTextName.getText().toString();
		if (name.isEmpty()) {
			return "Name darf nicht leer sein";
		} else if (name.length() > 50) {
			return "Name darf nicht länger als 50 Zeichen sein";
		}
		return null;
	}
}

