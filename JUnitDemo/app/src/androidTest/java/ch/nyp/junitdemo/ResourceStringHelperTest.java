package ch.nyp.junitdemo;

import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static junit.framework.Assert.assertEquals;

/**
 * Testklasse für {@link ResourceStringHelper}.
 * Instrumentation Test
 * <br/><br/>
 *
 * History:
 * 23.03.2015	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ResourceStringHelperTest {

	@Rule
	public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity
			.class);

	/**
	 * Testet, ob die Methode {@link ResourceStringHelper#getRestErrorMessage(Context, String, int)} korrekt
	 * funktioniert.
	 *
	 * @throws Exception Exception, welche die Methode
	 * {@link ResourceStringHelper#getRestErrorMessage(Context, String, int)} werfen könnte
	 */
	@Test
	public void testGetRestErrorMessage() throws Exception {
		MainActivity mainActivity = rule.getActivity();

		String restUrl = "/lernenden";
		int errorCode = 400;

		String expectedString = "Mindestens eine der gemachten Angaben ist fehlerhaft oder es " +
				"wurden mehr als 2 Fotos zum Upload angegeben.";
		String stringFromLanguageFile = ResourceStringHelper.getRestErrorMessage(mainActivity,
				restUrl, errorCode);
		assertEquals(expectedString,stringFromLanguageFile );
	}
}