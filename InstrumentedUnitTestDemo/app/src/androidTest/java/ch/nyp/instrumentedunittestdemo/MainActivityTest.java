package ch.nyp.instrumentedunittestdemo;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static junit.framework.Assert.assertEquals;

/**
 * InstrumentedUnitTest für die Klasse {@link MainActivity}<br/>
 * Der InstrumentedUnitTest testet die Validierung, indem die Methode
 * {@link MainActivity#validateFormFields()} aufgerufen/getestet wird.
 * <br/><br/>
 *
 * History:
 * 01.02.2018	1.0	Joel Holzer		Klasse erstellt
 *
 * @author Joel Holzer
 * @version 1.0
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity
			.class);

	/**
	 * Testet die Validierung (Methode {@link MainActivity#validateFormFields()}) mit einer leeren
	 * Eingabe im Textfeld
	 * .<br/>
	 * Erwartet: Fehlermeldung "Name darf nicht leer sein" wird zurückgegeben.
	 * @throws Throwable
	 */
	@Test
	public void testValidateFormFields_empty() throws Throwable {
		final MainActivity activity = rule.getActivity();

		final EditText editText = activity.findViewById(R.id.edittext_main_name);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editText.setText("");
				String errorMessage = activity.validateFormFields();
				assertEquals("Name darf nicht leer sein", errorMessage);
			}
		});
	}

	/**
	 * Testet die Validierung (Methode {@link MainActivity#validateFormFields()}) mit einer zu
	 * langen Eingabe (> 50 Zeichen) im Textfeld
	 * .<br/>
	 * Erwartet: Fehlermeldung "Name darf nicht länger als 50 Zeichen sein" wird zurückgegeben.
	 * @throws Throwable
	 */
	@Test
	public void testValidateFormFields_toLong() throws Throwable {
		final MainActivity activity = rule.getActivity();

		final EditText editText = activity.findViewById(R.id.edittext_main_name);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editText.setText("dassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddf");
				String errorMessage = activity.validateFormFields();
				assertEquals("Name darf nicht länger als 50 Zeichen sein", errorMessage);
			}
		});
	}

	/**
	 * Testet die Validierung (Methode {@link MainActivity#validateFormFields()}) mit einer
	 * gültigen Eingabe im Textfeld
	 * .<br/>
	 * Erwartet: null wird zurückgegeben
	 * @throws Throwable
	 */
	@Test
	public void testValidateFormFields_correct() throws Throwable {
		final MainActivity activity = rule.getActivity();

		final EditText editText = activity.findViewById(R.id.edittext_main_name);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editText.setText("Joel Holzer");
				String errorMessage = activity.validateFormFields();
				assertEquals(null, errorMessage);
			}
		});
	}
}