package ch.nyp.dynamicspinnerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.nyp.dynamicspinnerdemo.model.User;

/**
 * Adapter für den Spinner zur Auswahl des Lernenden.
 * Im Spinner wird jeder Lernende mit seinem Profilbild und seinem Vor- und Nachnamen angezeigt.
 * Das Profilbild wird im Moment noch von einem Default-Profilbild gefüllt.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer		Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class LernendeAdapter extends ArrayAdapter<User> {

	private int mSpinnerLayout;
	private LayoutInflater mLayoutInflater;

	/**
	 * Konstruktor. Initialisiert gewisse Member Variablen.
	 *
	 * @param context Context der Applikation.
	 * @param lernende Liste mit allen im Spinner anzuzeigenden Lernenden.
	 * @since 1.0
	 */
	public LernendeAdapter(Context context, List<User> lernende) {
		super(context, R.layout.layout_image_spinner_item_list);
		mSpinnerLayout = R.layout.layout_image_spinner_item_list;
		mLayoutInflater =  (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);

		this.addAll(lernende);
	}

	/**
	 * Gibt das View zurück, um den Lernenden an der übergebenen Position in einem
	 * Dropdown-Spinner anzuzeigen. Wird aufgerufen, wenn alle Einträge im Spinner angezeigt werden.
	 *
	 *
	 * @param position Position des anzuzeigenden Lernenden.
	 * @param convertView Das alte View, welches wiederbenutzt werden soll. Null wenn kein altes
	 * 					  View vorhanden ist.
	 * @param parent Das Eltern-View, zu welchem das Dropdown-View hinzugefügt werden soll.
	 * @return View für den Lernenden an der übergebenen Position in einem Dropdown-Spinner.
	 * @since 1.0
	 */
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getView(position, convertView, parent);
	}

	/**
	 * Gibt das View des Lernenden-Eintrags im Spinner zurück.
	 *
	 * @param position Position des anzuzeigenden Lernenden.
	 * @param convertView Das alte View, welches wiederbenutzt werden soll. Null wenn kein altes
	 * View vorhanden ist.
	 * @param parent Das Eltern-View, zu welchem das Dropdown-View hinzugefügt werden soll.
	 * @return View des Lernenden-Eintrags im Spinner.
	 * @since 1.0
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(mSpinnerLayout, null);

			viewHolder = new ViewHolder();
			viewHolder.userImageView = convertView.findViewById(R.id.image_image_spinner_item_image);
			viewHolder.userTextView = convertView.findViewById(R.id.text_image_spinner_item_text);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}

		String userName = getItem(position).getFirstName() + " " + getItem(position).getLastName();
		viewHolder.userTextView.setText(userName);

		return convertView;
	}

	/**
	 * ViewHolder werden eingesetzt, um in einer Liste ein "smoothly" Scrollen zu ermöglichen.
	 *
	 * @since 1.0
	 */
	private static class ViewHolder {
		ImageView userImageView;
		TextView userTextView;
	}
}
