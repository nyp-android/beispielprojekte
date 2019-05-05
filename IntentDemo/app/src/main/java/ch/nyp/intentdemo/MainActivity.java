package ch.nyp.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Einstiegsactivity.
 * Beinhaltet ein EditText, in welchem ein Text eingegeben werden kann, sowie ein Button, bei
 * wessen Klick, die {@link Activity2} geöffnet und in dieser der eingebene Text angezeigt wird.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	public static final String INTENT_KEY_DISPLAY_TEXT_ACTIVITY_2 = "TEXT_TO_DISPLAY_ACTIVITY_2";

	private EditText mTextToTransferEditText;

	/**
	 * OnClickListener für den Button "Start Activity 2".
	 * Startet das Activity 2 und übergibt den Text, welcher im Eingabefeld eingegeben wurde.
	 */
	private View.OnClickListener mOpenActivityOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View openActivityButton) {
			String inputText = mTextToTransferEditText.getText().toString();

			Intent intent = new Intent(getApplicationContext(), Activity2.class);
			Bundle bundle = new Bundle();
			bundle.putString(INTENT_KEY_DISPLAY_TEXT_ACTIVITY_2, inputText);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};

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
		setTitle(R.string.mainActivity_title);

		Button openActivityButton = findViewById(R.id.button_main_openActivity2);
		openActivityButton.setOnClickListener(mOpenActivityOnClickListener);

		mTextToTransferEditText = findViewById(R.id
				.editText_main_textToTransfer);
	}
}
