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

import com.google.android.material.R;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.google.android.material.picker.selector.DateRangeGridSelector;
import com.google.android.material.picker.selector.GridSelector;
import androidx.core.util.Pair;
import java.util.Calendar;

/**
 * A {@link Dialog} with a header, {@link MaterialCalendar<Pair<Calendar, Calendar>>}, and set of
 * actions.
 *
 * @hide
 */
@RestrictTo(Scope.LIBRARY_GROUP)
public class MaterialDateRangePickerDialogFragment
    extends MaterialPickerDialogFragment<Pair<Calendar, Calendar>> {

  public static MaterialDateRangePickerDialogFragment newInstance() {
    return newInstance(0);
  }

  public static MaterialDateRangePickerDialogFragment newInstance(int themeResId) {
    return newInstance(themeResId, MaterialPickerDialogFragment.DEFAULT_BOUNDS);
  }

  public static MaterialDateRangePickerDialogFragment newInstance(CalendarBounds calendarBounds) {
    return newInstance(0, calendarBounds);
  }

  public static MaterialDateRangePickerDialogFragment newInstance(
      int themeResId, CalendarBounds calendarBounds) {
    MaterialDateRangePickerDialogFragment materialDateRangePickerDialogFragment =
        new MaterialDateRangePickerDialogFragment();
    Bundle args = new Bundle();
    addArgsToBundle(args, themeResId, calendarBounds);
    materialDateRangePickerDialogFragment.setArguments(args);
    return materialDateRangePickerDialogFragment;
  }

  @Override
  protected int getDefaultThemeAttr() {
    return R.attr.materialDateRangePickerDialogTheme;
  }

  @Override
  protected GridSelector<Pair<Calendar, Calendar>> createGridSelector() {
    return new DateRangeGridSelector();
  }

  @Override
  protected String getHeaderText(Pair<Calendar, Calendar> selection) {
    if (selection == null) {
      return getContext().getResources().getString(R.string.mtrl_picker_range_header_prompt);
    }
    String startString = getSimpleDateFormat().format(selection.first.getTime());
    String endString = getSimpleDateFormat().format(selection.second.getTime());
    return getContext()
        .getResources()
        .getString(R.string.mtrl_picker_range_header_selected, startString, endString);
  }

  /** Returns the start date for the selection or null if the user has not yet confirmed. */
  @Nullable
  public Calendar getStart() {
    return getSelection() == null ? null : getSelection().first;
  }

  /** Returns the end date for the selection or null if the user has not yet confirmed. */
  @Nullable
  public Calendar getEnd() {
    return getSelection() == null ? null : getSelection().second;
  }
}
