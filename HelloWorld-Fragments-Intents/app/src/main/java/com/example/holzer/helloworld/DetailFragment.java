/*
 * Project: Mobile Ordering in Swiss Gastronomy
 * Classname: TODO
 * Author: Joel Holzer
 * Last Change:
 *	by:	Joel Holzer
 *	date: 25.05.2016
 * Copyright (c): Joel Holzer, Christian Bigler, BFH, 2015
 */
package com.example.holzer.helloworld;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);
		return rootView;
	}
}
