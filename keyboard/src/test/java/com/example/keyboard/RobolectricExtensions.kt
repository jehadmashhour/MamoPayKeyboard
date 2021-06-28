package com.example.keyboard

import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import org.junit.Assert.assertEquals
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.AttributeSetBuilder

/**
 * Project: Classera
 *
 * @author Jehad Abdalqader
 */

fun buildAttributeSet(attrs: AttributeSetBuilder.() -> Unit): AttributeSet {
    return with(Robolectric.buildAttributeSet()) {
        attrs()
        build()
    }
}

fun TextView.assertText(expected: String) {
    assertEquals(expected, this.text.toString())
}