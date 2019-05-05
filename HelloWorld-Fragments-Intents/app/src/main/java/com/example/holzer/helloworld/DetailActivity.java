package com.example.holzer.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		getSupportFragmentManager().beginTransaction().add(R.id.activity_detail_root_container,
				new DetailFragment())
				.commitAllowingStateLoss();
	}
}
