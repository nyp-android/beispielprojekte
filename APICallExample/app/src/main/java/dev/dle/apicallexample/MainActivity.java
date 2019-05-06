package dev.dle.apicallexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements ResultsFragment.OnFragmentInteractionListener,
    MainLaunchFragment.OnFragmentInteractionListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    FragmentUtils.loadFragment(this, MainLaunchFragment.newInstance());

  }





  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
