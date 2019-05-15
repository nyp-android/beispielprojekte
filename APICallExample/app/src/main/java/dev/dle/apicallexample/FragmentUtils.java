package dev.dle.apicallexample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class FragmentUtils {


  static synchronized void loadFragment(Activity app, Fragment fragmentToShow) {

    FragmentManager manager = app.getFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragmentPlaceholder, fragmentToShow);
    transaction.commit();
  }

  public static synchronized void switchFragments(Activity app, Fragment fragmentToShow,
      Fragment fragmentToHide) {
    FragmentManager manager = app.getFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.hide(fragmentToHide);
    transaction.show(fragmentToShow);
    transaction.commit();
  }

}
