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
package com.google.android.material.picker;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/**
 * A single row adapter representing the days of the week for {@link Calendar}.
 *
 * <p>This {@link android.widget.Adapter} respects the {@link Calendar#getFirstDayOfWeek()}
 * determined by {@link Locale#getDefault()}.
 *
 * @hide
 */
class DaysOfWeekAdapter extends BaseAdapter {

  private final Calendar calendar;
  private final int daysInWeek;
  private final int firstDayOfWeek;
  /** Style value from Calendar.NARROW_FORMAT unavailable before 1.8 */
  private static final int NARROW_FORMAT = 4;
  private static final int CALENDAR_DAY_STYLE =
      VERSION.SDK_INT >= VERSION_CODES.O ? NARROW_FORMAT : Calendar.SHORT;

  public DaysOfWeekAdapter() {
    calendar = Calendar.getInstance();
    calendar.clear();
    daysInWeek = calendar.getMaximum(Calendar.DAY_OF_WEEK);
    firstDayOfWeek = calendar.getFirstDayOfWeek();
  }

  @Override
  public Integer getItem(int position) {
    if (position >= daysInWeek) {
      return null;
    }
    return positionToDayOfWeek(position);
  }

  @Override
  public long getItemId(int position) {
    // There is only 1 row
    return 0;
  }

  @Override
  public int getCount() {
    return daysInWeek;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView day = (TextView) convertView;
    if (convertView == null) {
      day = new TextView(parent.getContext());
    }
    calendar.set(Calendar.DAY_OF_WEEK, positionToDayOfWeek(position));
    day.setText(
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, CALENDAR_DAY_STYLE, Locale.getDefault()));
    return day;
  }

  private int positionToDayOfWeek(int position) {
    // Day Constants start at 1
    int dayConstant = position + firstDayOfWeek;
    if (dayConstant > daysInWeek) {
      dayConstant = dayConstant - daysInWeek;
    }
    return dayConstant;
  }
}
