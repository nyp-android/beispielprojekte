package com.example.nyp.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Hauptactivity. Zeigt in einer CardView/RecyclerView verschiedene Cards an. <br/>
 * Jede Card wird mit einem Text und einem Bild angezeigt.<br/><br/>
 *
 * Das Layout für eine einzelne Card ist im XML "recyler_view_item" definiert.
 *
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
public class MainActivity extends AppCompatActivity {

    //Texte der Cards
    private String[] cardTexts = {
            "Card 1", "Card 2","Card 3","Card 4","Card 5","Card 6","Card 7","Card 8",
            "Card 9","Card 10","Card 11","Card 12","Card 13","Card 14","Card 15","Card 16","Card 17",
            "Card 18","Card 19","Card 20"
    };

    /**
     * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
     * Hier wird das Activity initialisiert. Das XML-File wird dem Activity zugeordnet.<br/>
     * Zudem wird hier der RecyclerView der {@link RecyclerViewAdapter} für die Darstellung der
     * einzelnen Cards zugeteilt.
     *
     * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
     * 							 being shut down then this Bundle contains the data it most
     * 							 recently supplied in onSaveInstanceState(Bundle). Note: Otherwise
     * 							 it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);

        //Führt dazu, dass immer 2 Cards pro Zeile angezeigt werden.
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        RecyclerView.Adapter recyclerViewAdapter = new RecyclerViewAdapter(context, cardTexts);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
