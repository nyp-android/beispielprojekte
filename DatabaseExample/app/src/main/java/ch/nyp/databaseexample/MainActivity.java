package ch.nyp.databaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.nyp.databaseexample.model.User;
import ch.nyp.databaseexample.persistence.AppDatabase;
import ch.nyp.databaseexample.persistence.UserDao;

/**
 * Einstiegsactivity.
 * Beinhaltet zwei Buttons, ein Button zum Speichern von 10 Users in der Datenbank, ein zweiter
 * Button zum Anzeigen aller bereits in der Datenbank gespeicherten Users.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	private UserDao mUserDao;

	/**
	 * OnClickListener für den Button "Save 10 Users".
	 */
	private View.OnClickListener mSaveUsersOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View openActivityButton) {
			List<User> usersToSave = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setFirstName("Vorname" + i);
				user.setLastName("Nachname" + i);
				usersToSave.add(user);
			}

			mUserDao.deleteAll();
			mUserDao.insertAll(usersToSave);
		}
	};

	/**
	 * OnClickListener für den Button "Users anzeigen".
	 */
	private View.OnClickListener mDisplayUsersOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View openActivityButton) {
			List<User> usersFromDatabase = mUserDao.getAll();
			String textToDisplay = "";
			for (User user : usersFromDatabase) {
				textToDisplay += "ID: " + user.getId() + "; Vorname: " + user.getFirstName()
						+ "; Nachname: " + user.getLastName() + "\n";
			}
			Toast.makeText(MainActivity.this, textToDisplay, Toast.LENGTH_SHORT).show();

		}
	};

	/**
	 * Wird beim Starten des Activity ausgeführt.
	 * Verbindet den JavaCode mit dem XML und initialisiert die Instanzvariablen.
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button saveUserButton = findViewById(R.id.button_main_saveUsers);
		saveUserButton.setOnClickListener(mSaveUsersOnClickListener);

		Button displayUsersButton = findViewById(R.id.button_main_displayUsers);
		displayUsersButton.setOnClickListener(mDisplayUsersOnClickListener);

		mUserDao = AppDatabase.getAppDb(getApplicationContext()).getUserDao();
	}
}
