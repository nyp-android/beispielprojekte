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

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import com.google.android.material.picker.selector.GridSelector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;

/**
 * Represents the days of a month with {@link TextView} instances for each day.
 *
 * <p>The number of rows is always equal to the maximum number of weeks that can exist across all
 * months (e.g., 6 for the GregorianCalendar).
 *
 * @hide
 */
@RestrictTo(Scope.LIBRARY_GROUP)
@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
public class MonthAdapter extends BaseAdapter {

  /**
   * The maximum number of weeks possible in any month. 6 for {@link java.util.GregorianCalendar}.
   */
  static final int MAXIMUM_WEEKS = Calendar.getInstance().getMaximum(Calendar.WEEK_OF_MONTH);

  private final Month month;
  private final int textViewSize;
  private final GridSelector<?> gridSelector;

  @VisibleForTesting
  public MonthAdapter(
      Context context, Month month, GridSelector<?> gridSelector) {
    this.month = month;
    textViewSize = MaterialCalendar.getDayHeight(context);
    this.gridSelector = gridSelector;
  }

  /**
   * Returns a {@link Calendar} object for the given grid position
   *
   * @param position Index for the item. 0 matches the {@link Calendar#getFirstDayOfWeek()} for the
   *     first week of the month represented by {@link Month}.
   * @return An {@link Calendar} representing the day at the position or null if the position does
   *     not represent a valid day in the month.
   */
  @Nullable
  @Override
  public Calendar getItem(int position) {
    if (position < month.daysFromStartOfWeekToFirstOfMonth()
        || position > lastPositionInMonth()) {
      return null;
    }
    return month.getDay(positionToDay(position));
  }

  @Override
  public long getItemId(int position) {
    return position / month.daysInWeek;
  }

  /**
   * Returns the number of days in a week times the maximum number of weeks in any possible month.
   * This number is at least the number of days in the month.
   *
   * @return The maximum valid position index
   */
  @Override
  public int getCount() {
    return month.daysInWeek * MAXIMUM_WEEKS;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView day = (TextView) convertView;
    if (convertView == null) {
      day = new TextView(parent.getContext());
      day.setHeight(textViewSize);
    }
    int offsetPosition = position - firstPositionInMonth();
    if (offsetPosition < 0 || offsetPosition >= month.daysInMonth) {
      day.setVisibility(View.INVISIBLE);
    } else {
      // The tag and text uniquely identify the view within the MaterialCalendar for testing
      day.setText(String.valueOf(offsetPosition + 1));
      day.setTag(month);
      day.setVisibility(View.VISIBLE);
    }
    Calendar item = getItem(position);
    if (item != null) {
      gridSelector.drawCell(day, item);
    }
    return day;
  }

  /**
   * Returns the index of the first position which is part of the month.
   *
   * <p>For example, this returns the position index representing February 1st. Since position 0
   * represents a day which must be the first day of the week, the first position in the month may
   * be greater than 0.
   */
  public int firstPositionInMonth() {
    return month.daysFromStartOfWeekToFirstOfMonth();
  }

  /**
   * Returns the index of the last position which is part of the month.
   *
   * <p>For example, this returns the position index representing November 30th. Since position 0
   * represents a day which must be the first day of the week, the last position in the month may
   * not match the number of days in the month.
   */
  public int lastPositionInMonth() {
    return month.daysFromStartOfWeekToFirstOfMonth() + month.daysInMonth - 1;
  }

  /**
   * Returns the day representing the provided adapter index
   *
   * @param position The adapter index
   * @return The day corresponding to the adapter index. May be non-positive for position inputs
   *     less than {@link MonthAdapter#firstPositionInMonth()}.
   */
  public int positionToDay(int position) {
    return position - month.daysFromStartOfWeekToFirstOfMonth() + 1;
  }

  /** True when a provided adapter position is within the calendar month */
  public boolean withinMonth(int position) {
    return position >= firstPositionInMonth() && position <= lastPositionInMonth();
  }
}
