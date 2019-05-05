package ch.nyp.databaseexample.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ch.nyp.databaseexample.model.User;

/**
 * Erstellt die Datenbank. Singleton-Klasse.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @see ch.nyp.databaseexample.model.User
 *
 * @author Joel Holzer
 * @version 1.0
 */
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

	private static final String DB_NAME = "db_demo_db";
	private static AppDatabase appDb;

	/**
	 * Gibt die aktive Instanz dieser Klasse zurück.
	 * Falls noch keine Instanz existiert, wird eine Instanz erstellt, d.h. es wird die DB im
	 * Filesystem erstellt und dann die Instanz zurückgegeben.
	 *
	 * @param context Context der App
	 * @return Instanz der App-DB.
	 */
	public static AppDatabase getAppDb(Context context) {
		if (appDb == null) {
			appDb = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
					.fallbackToDestructiveMigration()
					.allowMainThreadQueries()
					.build();
		}
		return appDb;
	}

	public abstract UserDao getUserDao();
}

