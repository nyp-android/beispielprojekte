package ch.nyp.junitdemo;

import android.content.Context;
import android.content.res.Resources;

/**
 * Beinhaltet statische Hilfsmethoden welche es vereinfachen, auf Texte von String-Resourcen
 * zuzugreifen.
 *
 * History:
 * 23.03.2015	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class ResourceStringHelper {

	/**
	 * Tritt bei einer REST-Webservice-Abfrage ein Fehler auf, so wird der aufgetretene Fehler
	 * mit einem Error-Code zurückgegeben.
	 * Um für den User der App eine verständliche Fehlermeldung auszugeben,
	 * werden die Fehlercodes mit Hilfe der String-Datei in lesbare Fehlermeldungen übersetzt.
	 *
	 * Die Fehlermeldungen in der String-Datei sind nach folgendem Schema aufgebaut:
	 * errors.rest.[url der webservice-methode, / ersetzt durch .].[Error-Code]
	 *
	 * z.B.
	 * errors.rest.login.400 für die Fehlermeldung mit dem Code 400 bei der Login-Methode
	 * errors.rest.beobachtung.add.400 für die Fehlermeldung mit dem Code 400 bei der "Add
	 * beobachtung"-Methode (url war beobachtung/add).
	 *
	 * Diese Methode ermittelt zum übergebenen Error-Code und der übergebenen URL der
	 * REST-Methode die Fehlermeldung aus der String-Datei und gibt diese zurück.
	 *
	 * @param activity Kontext der Applikation, d.h. das aktive Activity der App.
	 * @param restFunctionUrl URL der REST-Webservice-Methode, bei welcher der Fehler auftrag.
	 * @param errorCode Aufgetretener Fehlercode.
	 * @return Fehlermeldung.
	 */
	public static String getRestErrorMessage(Context activity, String restFunctionUrl,
										  int errorCode) {
		String errorsStringPrefix = "errors.rest";
		String errorStringKey = errorsStringPrefix + restFunctionUrl.replace("/", ".") + "." + errorCode;

		Resources activityResources = activity.getResources();

		return activityResources.getString(activityResources.getIdentifier(errorStringKey, "string", activity.getPackageName()));
	}
}
