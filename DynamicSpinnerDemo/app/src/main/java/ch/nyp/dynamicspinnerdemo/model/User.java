package ch.nyp.dynamicspinnerdemo.model;

/**
 * Repräsentiert einen User.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class User implements Comparable<User> {

	private int id;
	private String firstName;
	private String lastName;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * Zu implementierende Methode des Interface {@link Comparable}.
	 * Vergleicht den Vornamen des übergebenen Users mit dem eigenen Vornamen und gibt
	 * -1 zurück, wenn der Vorname alphabetisch kleiner ist (a ist kleiner als b),
	 * 0 wenn der Vorname gleich ist und 1 wenn der Vorname grösser ist.
	 * Wird benötigt, um eine Liste mit User aufsteigend nach deren Vornamen zu sortieren.
	 *
	 * @param user User, wessen Vorname mit dem eigenen Vornamen verglichen werden soll.
	 * @return -1, wenn der Vorname alphabetisch kleiner ist (a ist kleiner als b),
	 * 0 wenn der Vorname gleich ist und 1 wenn der Vorname grösser ist.
	 * @since 1.0
	 */
	@Override
	public int compareTo(User user) {
		return firstName.compareTo(user.getFirstName());
	}
}
