package ch.nyp.tabexample;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

/**
 * Hauptactivity der App. Wird beim App-Start angezeigt. <br/>
 * Beinhaltet ein TabLayout mit 2 Tabs.<br/>
 * Im Tab 1 wird das {@link Tab1Fragment} angezeigt, im Tab 2 das {@link Tab2Fragment}.<br/><br/>
 *
 * History:
 * <PRE>
 * 1.0	09.05.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert. So wird die Anzeige der Tabs aufbereitet, respektive
	 * verknüpft dass beim Klick auf ein Tab auch das richtige Fragment angezeigt wird.
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

		//Adapter erstellen, welcher je nach geklicktem Tab das richtige Fragment zur Anzeige
		// zurückgibt.
		TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

		// ViewPager zur Anzeige der Fragments mit dem Adapter koppeln
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_main);
		viewPager.setAdapter(tabPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main);

		//Listener für den Tabwechsel mit Swipe
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

		//Listener für den Tabwechsel über Tab
		tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
	}
}
