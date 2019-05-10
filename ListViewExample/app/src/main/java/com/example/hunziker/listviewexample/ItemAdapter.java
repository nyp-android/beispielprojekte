package com.example.hunziker.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter für die ListView
 * <p>
 * History:
 * 18.11.2016	1.0	Nathanael Hunziker  Klasse erstellt.
 *
 * @author Nathanael Hunziker
 * @version 1.0
 */
public class ItemAdapter extends ArrayAdapter<ItemObject> {
  
  private LayoutInflater mLayoutInflater;
  
  /**
   * Konstruktor. Initialisiert gewisse Member Variablen.
   *
   * @param context Context der Applikation.
   * @param items   Item in der Liste
   * @since 1.0
   */
  public ItemAdapter(Context context, List<ItemObject> items) {
    
    super(context, R.layout.layout_listview_item);
    addAll(items);
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  
  /**
   * Gibt die View eines einzelnen Eintrags in der Liste zurück.
   *
   * @param position    Position des anzuzeigenden Eintrags.
   * @param convertView Das alte View, welches wiederbenutzt werden soll. Null wenn kein altes
   *                    View vorhanden ist.
   * @param parent      Das Eltern-View.
   * @return View eines spezifischen ListView-Eintrags
   * @since 1.0
   */
  public View getView(int position, View convertView, ViewGroup parent) {
    
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.layout_listview_item, null);
      
      viewHolder = new ViewHolder();
      viewHolder.itemNameTextView = convertView.findViewById(R.id.textview_itemname_listview);
      viewHolder.itemNumberTextView = convertView.findViewById(R.id.textview_itemnumber_listview);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    
    String name = getItem(position).getItemName();
    viewHolder.itemNameTextView.setText(name);
    String number = String.valueOf(getItem(position).getItemNumber());
    viewHolder.itemNumberTextView.setText(number);
    
    return convertView;
  }
  
  public static class ViewHolder {
    
    TextView itemNameTextView;
    TextView itemNumberTextView;
  }
}
