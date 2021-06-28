package com.example.keyboard.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */

fun ViewGroup.inflate(@LayoutRes resource: Int) {
    inflate(context, resource, this)
}

fun TextView.append(string: String?, color: Int) {
    if (string == null || string.isEmpty()) {
        return
    }

    val spannable: Spannable = SpannableString(string)
    spannable.setSpan(
        ForegroundColorSpan(color),
        0,
        spannable.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    append(spannable)
}

fun View.parentView(): View {
    return (this as ViewGroup).getChildAt(0)
}

fun View.childs(): List<View> {
    return mutableListOf<View>().apply {
        for (index in 0 until (this as ViewGroup).childCount) {
            add(getChildAt(index))
        }
    }
}

fun ViewGroup.parentView(): View {
    return getChildAt(0)
}

fun ViewGroup.childs(): List<View> {
    return mutableListOf<View>().apply {
        for (index in 0 until childCount) {
            add(getChildAt(index))
        }
    }
}

