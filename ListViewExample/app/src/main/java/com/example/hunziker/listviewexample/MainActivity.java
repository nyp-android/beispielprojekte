package com.example.hunziker.listviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Einstiegsactivity.
 * Beinhaltet eine ListView mit dynamischen Einträgen.
 * <p>
 * History:
 * 18.11.2016	1.0	Nathanael Hunziker Klasse erstellt.
 *
 * @author Nathanael Hunziker
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //Testdaten erstellen
    List<ItemObject> itemList = new ArrayList<>();
    for (int i = 1; i <= 30; i++) {
      ItemObject item1 = new ItemObject();
      item1.setItemName("Random Item");
      item1.setItemNumber(i);
      itemList.add(item1);
    }
    
    ListView listView = findViewById(R.id.listview_main_demoList);
    listView.setAdapter(new ItemAdapter(this, itemList));
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log ItemCLickPosition to LogCat
        Log.e("OnItemListView", Integer.toString(position));
        
      }
    });
  }
}
