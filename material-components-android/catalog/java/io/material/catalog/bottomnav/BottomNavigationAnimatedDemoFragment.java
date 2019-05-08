/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.material.catalog.bottomnav;

import io.material.catalog.R;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.material.catalog.feature.DemoFragment;

/**
 * A fragment that displays a bottom nav that uses {@link
 * android.graphics.drawable.AnimatedStateListDrawable} for icons.
 */
public class BottomNavigationAnimatedDemoFragment extends DemoFragment {

  @Override
  public View onCreateDemoView(
      LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
    View view =
        layoutInflater.inflate(
            R.layout.cat_bottom_navs_animated, viewGroup, false /* attachToRoot */);
    return view;
  }
}
