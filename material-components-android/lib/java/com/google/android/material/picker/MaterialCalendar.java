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

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.picker.selector.GridSelector;
import com.google.android.material.resources.MaterialAttributes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import java.util.Calendar;
import java.util.LinkedHashSet;

/**
 * Fragment for a days of week {@link Calendar} represented as a header row of days labels and
 * {@link GridView} of days backed by {@link MonthAdapter}.
 *
 * @hide
 */
@RestrictTo(Scope.LIBRARY_GROUP)
public final class MaterialCalendar<S> extends Fragment {

  private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
  private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
  private static final String CALENDAR_BOUNDS_KEY = "CALENDAR_BOUNDS_KEY";

  @VisibleForTesting
  @RestrictTo(Scope.LIBRARY_GROUP)
  public static final Object VIEW_PAGER_TAG = "VIEW_PAGER_TAG";

  private final LinkedHashSet<OnSelectionChangedListener<S>> onSelectionChangedListeners =
      new LinkedHashSet<>();

  private int themeResId;
  private GridSelector<S> gridSelector;
  private CalendarBounds calendarBounds;
  private MonthsPagerAdapter monthsPagerAdapter;

  /**
   * Creates a {@link MaterialCalendar} with {@link GridSelector#drawCell(View, Calendar)} applied
   * to each cell.
   *
   * @param gridSelector Controls the highlight state of the {@link MaterialCalendar}
   * @param <T> Type returned from selections in this {@link MaterialCalendar} by {@link
   *     MaterialCalendar#getSelection()}
   */
  public static <T> MaterialCalendar<T> newInstance(
      GridSelector<T> gridSelector, int themeResId, CalendarBounds calendarBounds) {
    MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
    Bundle args = new Bundle();
    args.putInt(THEME_RES_ID_KEY, themeResId);
    args.putParcelable(GRID_SELECTOR_KEY, gridSelector);
    args.putParcelable(CALENDAR_BOUNDS_KEY, calendarBounds);
    materialCalendar.setArguments(args);
    return materialCalendar;
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle bundle) {
    super.onSaveInstanceState(bundle);
    bundle.putInt(THEME_RES_ID_KEY, themeResId);
    bundle.putParcelable(GRID_SELECTOR_KEY, gridSelector);
    bundle.putParcelable(CALENDAR_BOUNDS_KEY, calendarBounds);
  }

  @Override
  public void onCreate(@Nullable Bundle bundle) {
    super.onCreate(bundle);
    Bundle activeBundle = bundle == null ? getArguments() : bundle;
    themeResId = activeBundle.getInt(THEME_RES_ID_KEY);
    gridSelector = activeBundle.getParcelable(GRID_SELECTOR_KEY);
    calendarBounds = activeBundle.getParcelable(CALENDAR_BOUNDS_KEY);
  }

  @NonNull
  @Override
  public View onCreateView(
      @NonNull LayoutInflater layoutInflater,
      @Nullable ViewGroup viewGroup,
      @Nullable Bundle bundle) {
    ContextThemeWrapper themedContext = new ContextThemeWrapper(getContext(), themeResId);
    LayoutInflater themedInflater = layoutInflater.cloneInContext(themedContext);

    Month earliestMonth = calendarBounds.getStart();
    Month latestMonth = calendarBounds.getEnd();
    Month currentMonth = calendarBounds.getCurrent();

    final View root = themedInflater.inflate(R.layout.mtrl_calendar, viewGroup, false);
    GridView daysHeader = root.findViewById(R.id.calendar_days_header);
    daysHeader.setAdapter(new DaysOfWeekAdapter());
    daysHeader.setNumColumns(earliestMonth.daysInWeek);

    ViewPager monthsPager = root.findViewById(R.id.month_pager);
    monthsPager.setTag(VIEW_PAGER_TAG);
    monthsPager.setLayoutParams(
        new LayoutParams(
            /* width= */ LayoutParams.MATCH_PARENT,
            /* height= */ MonthAdapter.MAXIMUM_WEEKS * getDayHeight(getContext())));
    monthsPagerAdapter =
        new MonthsPagerAdapter(
            getChildFragmentManager(),
            gridSelector,
            earliestMonth,
            latestMonth,
            currentMonth,
            new OnDayClickListener() {

              @Override
              public void onDayClick(Calendar day) {
                gridSelector.select(day);
                monthsPagerAdapter.notifyDataSetChanged();
                for (OnSelectionChangedListener<S> listener : onSelectionChangedListeners) {
                  listener.onSelectionChanged(gridSelector.getSelection());
                }
              }
            });
    monthsPager.setAdapter(monthsPagerAdapter);
    monthsPager.setCurrentItem(monthsPagerAdapter.getStartPosition());
    addMonthChangeListeners(root);
    return root;
  }

  public final S getSelection() {
    return gridSelector.getSelection();
  }

  boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> listener) {
    return onSelectionChangedListeners.add(listener);
  }

  boolean removeOnSelectionChangedListener(OnSelectionChangedListener<S> listener) {
    return onSelectionChangedListeners.remove(listener);
  }

  void clearOnSelectionChangedListeners() {
    onSelectionChangedListeners.clear();
  }

  interface OnSelectionChangedListener<S> {

    void onSelectionChanged(S selection);
  }

  interface OnDayClickListener {

    void onDayClick(Calendar day);
  }

  /** Returns the pixel height of each {@link android.view.View} representing a day. */
  @Px
  static int getDayHeight(Context context) {
    return MaterialAttributes.resolveMinimumAccessibleTouchTarget(context);
  }

  private void addMonthChangeListeners(View root) {
    final ViewPager monthPager = root.findViewById(R.id.month_pager);
    final MaterialButton monthDropSelect = root.findViewById(R.id.month_drop_select);
    monthDropSelect.setText(monthPager.getAdapter().getPageTitle(monthPager.getCurrentItem()));
    final MaterialButton monthPrev = root.findViewById(R.id.month_previous);
    final MaterialButton monthNext = root.findViewById(R.id.month_next);
    monthPager.addOnPageChangeListener(
        new SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            calendarBounds =
                CalendarBounds.create(
                    calendarBounds.getStart(),
                    calendarBounds.getEnd(),
                    monthsPagerAdapter.getPageMonth(position));
            monthDropSelect.setText(monthsPagerAdapter.getPageTitle(position));
          }
        });

    monthNext.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            if (monthPager.getCurrentItem() + 1 < monthPager.getAdapter().getCount()) {
              monthPager.setCurrentItem(monthPager.getCurrentItem() + 1);
            }
          }
        });
    monthPrev.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            if (monthPager.getCurrentItem() - 1 >= 0) {
              monthPager.setCurrentItem(monthPager.getCurrentItem() - 1);
            }
          }
        });
  }
}
