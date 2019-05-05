package ch.nyp.junitdemo;

/**
 * Beinhaltet eine Methode, welche prüft ob ein Text mehr als 3 A beinhaltet.
 * <br/><br/>
 *
 * History:
 * 23.03.2015	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class StringHelper {

	/**
	 * Prüft ob der übergebene String mehr als 3A beinhaltet.
	 *
	 * @param stringToCompare String, welcher geprüft werden soll
	 * @return True wenn mehr als 3A im String, false wenn nicht.
	 */
	public static boolean checkIfStringHasMoreThan3As(String stringToCompare) {
		int count = 0;
		for(int i= 0; i < stringToCompare.length(); i++) {
			if(stringToCompare.charAt(i)== 'A') {
				count++;
			}
		}

		if (count > 3) {
			return true;
		}
		return false;
	}
}
