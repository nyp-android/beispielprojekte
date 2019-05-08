/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.material.elevation;

import com.google.android.material.R;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;
import androidx.core.graphics.ColorUtils;

/** Utility for calculating elevation overlay alpha values and colors. */
public class ElevationOverlayProvider {

  private static final float FORMULA_MULTIPLIER = 4.5f;
  private static final float FORMULA_OFFSET = 2f;

  private final boolean elevationOverlaysEnabled;
  private final int elevationOverlaysColor;
  private final int colorSurface;
  private final float displayDensity;

  public ElevationOverlayProvider(Context context) {
    this.elevationOverlaysEnabled =
        MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlaysEnabled, false);
    this.elevationOverlaysColor =
        MaterialColors.getColor(context, R.attr.elevationOverlaysColor, Color.TRANSPARENT);
    this.colorSurface = MaterialColors.getColor(context, R.attr.colorSurface, Color.TRANSPARENT);
    this.displayDensity = context.getResources().getDisplayMetrics().density;
  }

  /**
   * Applies the calculated elevation overlay (@see #layerOverlay(int, float)) only if the current
   * theme's {@code R.attr.elevationOverlaysEnabled} is true and the {@code backgroundColor} matches
   * the theme's surface color ({@code R.attr.colorSurface}); otherwise returns the {@code
   * backgroundColor}.
   */
  @ColorInt
  public int layerOverlayIfNeeded(@ColorInt int backgroundColor, float elevation) {
    if (elevationOverlaysEnabled && isSurfaceColor(backgroundColor)) {
      return layerOverlay(backgroundColor, elevation);
    } else {
      return backgroundColor;
    }
  }

  /**
   * Calculates a color that represents the layering of the current theme's {@code
   * R.attr.elevationOverlaysColor} on top of the {@code backgroundColor}.
   *
   * <p>An alpha level is applied to the {@code R.attr.elevationOverlaysColor} by using a formula
   * that is based on the provided {@code elevation} value.
   */
  @ColorInt
  public int layerOverlay(@ColorInt int backgroundColor, float elevation) {
    float overlayAlpha = calculateOverlayAlphaFraction(elevation);
    return MaterialColors.layer(backgroundColor, elevationOverlaysColor, overlayAlpha);
  }

  /**
   * Calculates the alpha value, between 0 and 255, that should be used with the elevation overlay
   * color, based on the provided {@code elevation} value.
   */
  public int calculateOverlayAlpha(float elevation) {
    return Math.round(calculateOverlayAlphaFraction(elevation) * 255);
  }

  /**
   * Calculates the alpha fraction, between 0 and 1, that should be used with the elevation overlay
   * color, based on the provided {@code elevation} value.
   */
  public float calculateOverlayAlphaFraction(float elevation) {
    if (displayDensity <= 0 || elevation <= 0) {
      return 0;
    }
    float elevationDp = elevation / displayDensity;
    float alphaFraction =
        (FORMULA_MULTIPLIER * (float) Math.log1p(elevationDp) + FORMULA_OFFSET) / 100;
    return Math.min(alphaFraction, 1);
  }

  /** Returns the current theme's boolean value for {@code R.attr.elevationOverlaysEnabled}. */
  public boolean isOverlaysEnabled() {
    return elevationOverlaysEnabled;
  }

  /** Returns the current theme's color int value for {@code R.attr.elevationOverlaysColor}. */
  @ColorInt
  public int getOverlaysColor() {
    return elevationOverlaysColor;
  }

  /** Returns the current theme's color int value for {@code R.attr.colorSurface}. */
  @ColorInt
  public int getSurfaceColor() {
    return colorSurface;
  }

  /**
   * Returns the current theme's color int value for {@code R.attr.colorSurface}, with an elevation
   * overlay applied if needed (@see #layerOverlayIfNeeded(int, float)).
   */
  @ColorInt
  public int getSurfaceColorWithOverlayIfNeeded(float elevation) {
    return layerOverlayIfNeeded(colorSurface, elevation);
  }

  private boolean isSurfaceColor(@ColorInt int color) {
    return ColorUtils.setAlphaComponent(color, 255) == colorSurface;
  }
}
