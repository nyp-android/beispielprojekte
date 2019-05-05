package ch.nyp.junitdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testklasse für {@link StringHelper}.
 * Lokaler JUnit-Test.
 * <br/><br/>
 *
 * History:
 * 21.11.2016	1.0	Joel Holzer		Klasse erstellt
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class StringHelperTest {

	/**
	 * Testet, ob die Methode {@link StringHelper#checkIfStringHasMoreThan3As(String)} korrekt
	 * funktioniert.
	 *
	 * @throws Exception Exception, welche die Methode
	 * {@link StringHelper#checkIfStringHasMoreThan3As(String)} werfen könnte
	 */
	@Test
	public void testCheckIfStringHasMoreThan3As() throws Exception {
		String stringWith2As = "bbAbbcccAwleiie";
		String stringWith3As = "bbAbbcccccAcdA";
		String stringWith4As = "bbbbbAAccdeeeeAA";
		String stringWith3AsInARow = "AAAbbbdkdkdkd";
		String stringWith4AsInARow = "AAAAbbbdkdkdkd";

		assertFalse(StringHelper.checkIfStringHasMoreThan3As(stringWith2As));
		assertFalse(StringHelper.checkIfStringHasMoreThan3As(stringWith3As));
		assertTrue(StringHelper.checkIfStringHasMoreThan3As(stringWith4As));
		assertFalse(StringHelper.checkIfStringHasMoreThan3As(stringWith3AsInARow));
		assertTrue(StringHelper.checkIfStringHasMoreThan3As(stringWith4AsInARow));
	}
}