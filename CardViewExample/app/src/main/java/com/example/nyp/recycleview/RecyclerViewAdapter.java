package com.example.nyp.recycleview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter mit welchem die CardView mit Daten gefüllt wird. <br/>
 * Der Adapter ist dazu da, die Daten aus einem Array in die Cards abzufüllen. Für jeden Eintrag
 * im übergebenen Array wird eine Card angezeigt.
 * <br/><br/>
 *
 * History:
 * <PRE>
 * 1.0	21.11.2016	 Nathanael Hunziker		Klasse erstellt
 * </PRE>
 *
 * @author Nathanael Hunziker
 * @version 1.0
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private String[] mCardTitles;
	private Context mContext;

    /**
     * Konstruktor. Initialisiert die Instanzvariablen.
	 *
     * @param context Context der App.
     * @param cardTitles Array welches die Titeln aller Cards beinhaltet.
     */
    public RecyclerViewAdapter(Context context, String[] cardTitles){
        this.mContext = context;
        this.mCardTitles = cardTitles;
    }

	/**
	 * Erstellt einen neuen ViewHolder für eine einzelne Cards.
	 * ViewHolder ermöglichen ein Smoothly Scrolling.
	 *
	 * @param parent
	 * @param viewType
	 * @return ViewHolder für eine einzelne Card.
	 */
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

	/**
	 * Wird von der RecyclerView aufgerufen um die Daten in einer einzelnen Card anzuzeigen.
	 *
	 * @param viewHolder ViewHolder zur Anzeige der Card an der übergebenen Position.
	 * @param position Position der Card, wessen Inhalt angezeigt werden soll.
	 */
	@Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.textView.setText(mCardTitles[position]);
    }

	/**
	 * Gibt die Anzahl Einträge in der RecyclerView zurück.
	 *
	 * @return Anzahl Einträge in der RecyclerView.
	 */
	@Override
    public int getItemCount(){
        return mCardTitles.length;
    }

	/**
	 * Bereitet den ViewHolder auf, d.h. ordnet die GUI-Elemente aus dem XML an Java-Objekten zu.
	 */
	public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.recycler_textview_cardtext);
			imageView = (ImageView) v.findViewById(R.id.recycler_imageview_image);
        }
    }
}