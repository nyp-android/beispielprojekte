package ch.nyp.databaseexample.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ch.nyp.databaseexample.model.User;

/**
 * Beinhaltet Methoden, welche mit der User-Tabelle der SQLite-Datenbank auf dem Gerät
 * operieren (z.B. User speichern, User ermitteln).
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @see ch.nyp.databaseexample.model.User
 *
 * @author Joel Holzer
 * @version 1.0
 */
@Dao
public interface UserDao {
	@Query("SELECT * FROM user")
	List<User> getAll();

	@Query("DELETE FROM user")
	void deleteAll();

	@Insert
	void insertAll(List<User> users);
}
