package com.example.holzer.helloworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{

	private TextView textView;

	private View.OnClickListener mButtonOnclickListener = new View.OnClickListener() {
		@Override
		public void onClick(View button1) {;
			Intent intent = new Intent();
			intent.setClass(button1.getContext(), DetailActivity.class);
			startActivity(intent);
			startActivityForResult(intent, 2000);
		}
	};

	private View.OnClickListener mTextViewOnclickListener = new View.OnClickListener() {
		@Override
		public void onClick(View textViewLocal) {

			String textViewValue = textView.getText().toString();
			Integer countdown = Integer.parseInt(textViewValue);
			countdown--;
			textView.setText("" + countdown);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
		Button button = (Button) rootView.findViewById(R.id.main_button_open_activity);
		button.setOnClickListener(mButtonOnclickListener);

		textView = (TextView) rootView.findViewById(R.id.main_textview_text);
		textView.setText("20");

		Spinner spinnerSettings = (Spinner) rootView.findViewById(R.id.main_spinner_settings);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R
				.array.settings_beobachtung_spinner, android.R.layout.simple_spinner_item);
		spinnerSettings.setAdapter(adapter);

		return rootView;
	}
}
