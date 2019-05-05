package ch.nyp.toastexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private EditText mEditTextName;
	private Button mButtonSave;

	private View.OnClickListener mSaveOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View sendButton) {
			//Eingegebener Text in einem Toast, welches 3.5 Sekunden (Toast.LENGTH_LONG) angezeigt
			// wird, ausgeben
			String name = mEditTextName.getText().toString();
			Toast toast = Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG);
			toast.show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mEditTextName = findViewById(R.id.edittext_main_name);
		mButtonSave = findViewById(R.id.button_main_save);

		mButtonSave.setOnClickListener(mSaveOnClickListener);
	}
}
