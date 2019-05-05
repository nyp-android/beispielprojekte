package ch.nyp.intentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity, welche durch den Intent (beim Button-Klick in {@link MainActivity}) aufgerufen wird.
 * Gibt den Text, welcher mit dem Intent übergeben wurde, in einem TextView aus.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class Activity2 extends AppCompatActivity {

	/**
	 * Wird beim Starten des Activity ausgeführt.
	 * Verbindet den JavaCode mit dem XML und initialisiert die Instanzvariablen.
	 * Gibt zudem den vom Intent (MainActivity) erhaltenen Text aus.
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * 							 gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		setTitle(R.string.activity2_title);

		Bundle intentBundle = this.getIntent().getExtras();

		TextView transferedTextView =  this.findViewById(R.id.textView_2_transferedText);
		transferedTextView.setText(intentBundle.getString(MainActivity.INTENT_KEY_DISPLAY_TEXT_ACTIVITY_2));
	}
}
