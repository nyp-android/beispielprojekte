package ch.nyp.tabexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Der ViewPager wird benötigt, um beim Klick auf einen Tab oder bei einem Swipe im ViewPager das
 * richtige Fragment anzuzeigen <br/>
 * Im Tab 1 wird das Fragment {@link Tab1Fragment} angezeigt, im Tab 2 {@ink Tab2Fragment}.<br/><br/>
 *
 * History:
 * <PRE>
 * 1.0	09.05.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
	private static final int TAB_POSITION_TAB1 = 0;
	private static final int TAB_POSITION_TAB2 = 1;

	/**
	 * Konstruktor. Ruft den Super-Konstruktor auf.
	 * @param fragmentManager FragmentManager
	 */
	public TabPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	/**
	 * Gibt je nach übergebener Tab-Position das Fragment zurück, welches im ViewPager angezeigt
	 * werden soll. <br/>
	 * Im Tab 1 (Position 0) wird das Fragment {@link Tab1Fragment} angezeigt, im Tab 2 (Position
	 * 1){@ink Tab2Fragment}.
	 *
	 * @param position Tab-Position, wessen Fragment zurückgegeben werden soll.
	 * @return Fragment, welches im ViewPager angezeigt werden soll.
	 */
	@Override
	public Fragment getItem(int position) {

		switch (position) {
			case TAB_POSITION_TAB1:
				Tab1Fragment tab1Fragment = new Tab1Fragment();
				return tab1Fragment;
			case TAB_POSITION_TAB2:
				Tab2Fragment tab2Fragment = new Tab2Fragment();
				return tab2Fragment;
		}
		return null;
	}

	/**
	 * Gibt die Anzahl Pages/Tabs zurück.
	 *
	 * @return Anzahl Pages/Tabs.
	 */
	@Override
	public int getCount() {
		return 2;
	}
}
