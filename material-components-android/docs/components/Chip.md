<!--docs:
title: "Chips"
layout: detail
section: components
excerpt: "Chips represent complex entities in small blocks, such as a contact."
iconId: chip
path: /catalog/chip/
-->

# Chips

A `Chip` represents a complex entity in a small block, such as a contact. It is
a rounded button that consists of a label, an optional chip icon, and an
optional close icon. A chip can either be clicked or toggled if it is checkable.

Chips may be placed in a `ChipGroup`, which can be configured to lay out its
chips in a single horizontal line or reflowed across multiple lines. If a chip
group contains checkable chips, it can also control the multiple-exclusion scope
for its set of chips so that checking one chip unchecks all other chips in the
group.

You can also directly use a standalone `ChipDrawable` in contexts that require a
Drawable. For example, an auto-complete enabled text field can replace snippets
of text with a `ChipDrawable` using a span to represent it as a semantic entity.

## Design & API Documentation

-   [Material Design guidelines:
    Chips](https://material.io/go/design-chips)
    <!--{: .icon-list-item.icon-list-item--spec }-->
-   [Class
    definition](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/chip/Chip.java)
    <!--{: .icon-list-item.icon-list-item--link }-->
-   [Class
    overview](https://developer.android.com/reference/com/google/android/material/chip/Chip)
    <!--{: .icon-list-item.icon-list-item--link }--> <!--{: .icon-list }-->

## Usage

The `Chip` widget provides a complete implementation of Material Design's chip
component. Example code of how to include the widget in your layout:

```xml
<com.google.android.material.chip.Chip
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/hello_world"/>
```

### Material Styles

Using `Chip` with an updated Material theme (`Theme.MaterialComponents`) will
provide the correct updated Material styles to your chips by default. If you
need to use an updated Material chip and your application theme does not inherit
from an updated Material theme, you can apply one of the updated Material styles
directly to your widget in XML.

#### Entry Chip

Use entry chips to represent a complex piece of information in a compact form.

This style usually contains an optional chip icon, optional close icon, and is
optionally checkable.

```xml
<com.google.android.material.chip.Chip
    style="@style/Widget.MaterialComponents.Chip.Entry"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:chipIcon="@drawable/ic_avatar_circle_24"
    android:text="@string/hello_world"/>
```

Note: Entry chips are usually used with a standalone `ChipDrawable`.

#### Filter Chip

Use filter chips containing tags or descriptive words to filter a collection.

This style usually contains an optional chip icon, an optional close icon, and
is always checkable.

```xml
<com.google.android.material.chip.Chip
    style="@style/Widget.MaterialComponents.Chip.Filter"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/hello_world"/>
```

Note: Filter chips are usually placed within a `ChipGroup`.

#### Choice Chip

Use choice chips to help users make a single selection from a finite set of
options.

This style usually contains an optional chip icon and is always checkable.

```xml
<com.google.android.material.chip.Chip
    style="@style/Widget.MaterialComponents.Chip.Choice"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/hello_world"/>
```

Note: Choice chips are usually placed within a `ChipGroup`.

#### Action Chip (Default)

Use action chips to trigger an action that is contextual to primary content.

This style usually contains an optional chip icon and is never checkable.

```xml
<com.google.android.material.chip.Chip
    style="@style/Widget.MaterialComponents.Chip.Action"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:chipIcon="@drawable/ic_action_24"
    android:text="@string/hello_world"/>
```

### Chip Touch Target

To ensure that a `Chip` is accessible, use a MaterialComponents.Chip style which
uses an
[InsetDrawable](https://developer.android.com/reference/android/graphics/drawable/InsetDrawable)
to extend chip's touch target (when necessary) to meet Android's recommended
[accessibility touch target size](https://support.google.com/accessibility/android/answer/7101858).
Developers can override chip's minimum touch target size using
`app:chipMinTouchTargetSize`.

### Chip Attributes

Feature       | Relevant attributes
--------------| -----------------------------
Label         | `android:text` <br> `android:textAppearance`
Shape         | `app:chipCornerRadius`
Size          | `app:chipMinHeight`
Background    | `app:chipBackgroundColor`
Border        | `app:chipStrokeColor` <br> `app:chipStrokeWidth`
Ripple        | `app:rippleColor`
Chip Icon     | `app:chipIconVisible` <br> `app:chipIcon` <br> `app:chipIconTint` <br> `app:chipIconSize`
Close Icon    | `app:closeIconVisible` <br> `app:closeIcon` <br> `app:closeIconSize` <br> `app:closeIconTint`
Checkable     | `android:checkable`
Checked Icon  | `app:checkedIconVisible` <br> `app:checkedIcon`
Motion        | `app:showMotionSpec` <br> `app:hideMotionSpec`
Paddings      | `app:chipStartPadding` <br> `app:iconStartPadding` <br> `app:iconEndPadding` <br> `app:textStartPadding` <br> `app:textEndPadding` <br> `app:closeIconStartPadding` <br> `app:closeIconEndPadding` <br> `app:chipEndPadding`
TouchTarget   | `app:chipMinTouchTargetSize` <br> `app:ensureMinTouchTargetSize`
### Theme Attribute Mapping

#### Entry Chip, Filter Chip, Choice Chip, Action Chip (Default)

```
style="@style/Widget.MaterialComponents.Chip.Entry"
style="@style/Widget.MaterialComponents.Chip.Filter"
style="@style/Widget.MaterialComponents.Chip.Choice"
style="@style/Widget.MaterialComponents.Chip.Action" (default)
```

Component Attribute           | Default Theme Attribute Value
----------------------------- | -------------------------------
`android:textAppearance`      | `textAppearanceBody2`
`android:textColor` (enabled) | `colorOnSurface` at 87% opacity
`android:textColor`           | `colorOnSurface` at 33% opacity
`chipBackgroundColor`         | `colorOnSurface`
`chipSurfaceColor`            | `colorSurface`
`chipStrokeColor`             | `colorOnSurface`
`rippleColor`                 | `colorOnSurface`
`closeIconTint`               | `colorOnSurface`

### Handling Clicks

Call `setOnClickListener(OnClickListener)` to register a callback to be invoked
when the chip is clicked.

```java
Chip chip = (Chip) findViewById(R.id.chip);

chip.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        // Handle the click.
    }
});
```

Or, call `setOnCheckedChangeListener(OnCheckedChangeListener)` to register a
callback to be invoked when the chip is toggled.

```java
Chip chip = (Chip) findViewById(R.id.chip);

chip.setOnCheckedChangeListener(new setOnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton view, boolean isChecked) {
        // Handle the toggle.
    }
});
```

Call `setOnCloseIconClickListener(OnClickListener)` to register a callback to be
invoked when the chip's close icon is clicked.

```java
Chip chip = (Chip) findViewById(R.id.chip);

chip.setOnCloseIconClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        // Handle the click on the close icon.
    }
});
```

### RTL-friendly Chip Layout

Call `setLayoutDirection(int)` with `View.LAYOUT_DIRECTION_LOCALE` to ensure
that the Chip's ancestor `TextView` renders the text with proper paddings.
Without this, the initial rendering may look like the text has its padding set
according to LTR direction.

```java
Chip chip = (Chip) findViewById(R.id.chip);
chip.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
```

### ChipGroup

A `ChipGroup` contains a set of `Chip`s and manages their layout and
multiple-exclusion scope, similarly to a `RadioGroup`.

#### Layout Mode

A `ChipGroup` will by default reflow its chips across multiple lines.

```xml
<com.google.android.material.chip.ChipGroup
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

  <!-- Chips can be declared here, or added dynamically. -->

</com.google.android.material.chip.ChipGroup>
```

A `ChipGroup` can also constrain its chips to a single horizontal line using the
`app:singleLine` attribute. If you do so, you'll usually want to wrap the
`ChipGroup` in a `HorizontalScrollView`.

```xml
<HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
  <com.google.android.material.chip.ChipGroup
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:singleLine="true">

    <!-- Chips can be declared here, or added dynamically. -->

  </com.google.android.material.chip.ChipGroup>
</HorizontalScrollView>
```

#### Chip Spacing
A `ChipGroup` can insert spacing between chips in a row or between rows of chips
using the `app:chipSpacing` attribute. Different horizontal and vertical spacing
can be set using the `app:chipSpacingHorizontal` and `app:chipSpacingVertical`
attributes.

#### Multiple Exclusion Scope

A `ChipGroup` can be configured to only allow a single chip to be checked at a
time using the `app:singleSelection` attribute.

```xml
<com.google.android.material.chip.ChipGroup
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:singleSelection="true">

  <!-- ... -->

</com.google.android.material.chip.ChipGroup>
```

#### Handling Checked Chips

Call `setOnCheckedChangeListener(OnCheckedChangeListener)` to register a
callback to be invoked when the checked chip changes in this group. This
callback is only invoked in single selection mode.

```java
ChipGroup chipGroup = (ChipGroup) findViewById(R.id.chip_group);

chipGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
        // Handle the checked chip change.
    }
});
```

Or, call `getCheckedChipId()` at any time to get the checked chip. The return
value is only valid in single selection mode.

### ChipGroup Attributes
Feature         | Relevant Attributes
----------------| -----------------------------
Layout          |`app:singleLine`
Selection       | `app:singleSelection`
Spacing         |`app:chipSpacing` <br> `app:chipSpacingHorizontal` <br> `chipSpacingVertical`

### Standalone ChipDrawable

A standalone `ChipDrawable` can be used in contexts that require a `Drawable`.
The most obvious use case is in text fields that "chipify" contacts, commonly
found in communications apps.

To use a `ChipDrawable`, first create a chip resource in `res/xml`. Note that
you must use the `<chip` tag in your resource file.

**res/xml/standalone_chip.xml**

```xml
<chip
    xmlns:app="http://schemas.android.com/apk/res-auto"
    app:chipIcon="@drawable/ic_avatar_circle_24"
    android:text="@string/hello_world"/>
```

After inflating it dynamically, you can treat it as any other `Drawable`.

**MainActivity.java**

```java
// Inflate from resources.
ChipDrawable chip = ChipDrawable.createFromResource(getContext(), R.xml.standalone_chip);

// Use it as a Drawable however you want.
chip.setBounds(0, 0, chip.getIntrinsicWidth(), chip.getIntrinsicHeight());
ImageSpan span = new ImageSpan(chip);

Editable text = editText.getText();
text.setSpan(span, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
```

#### Styles

Entry Chip is the default Material style for standalone `ChipDrawable`s, but you
can apply any of the other styles using a `style` attribute.

```xml
<chip
    xmlns:app="http://schemas.android.com/apk/res-auto"
    style="@style/Widget.MaterialComponents.Chip.Filter"
    app:chipIcon="@drawable/ic_avatar_circle_24"
    android:text="@string/hello_world"/>
```

#### Attributes

All the attributes on `Chip` can be applied to a `ChipDrawable` resource.
