package com.example.keyboard.views

import android.app.Activity
import android.os.Build
import android.util.AttributeSet
import com.example.keyboard.R
import com.example.keyboard.assertText
import com.example.keyboard.buildAttributeSet
import com.example.keyboard.extensions.*
import com.google.android.material.button.MaterialButton
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Project: Classera
 *
 * @author Jehad Abdalqader
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class NumberKeyboardViewTest {
    private lateinit var numberKeyboardView: NumberKeyboardView
    private lateinit var decimalTextView: DecimalTextView
    private val activity = Robolectric.buildActivity(Activity::class.java).get()
    private val currency = activity.getString(R.string.aed)
    private val maxDecimalDigit = 2
    private val maxInputLength = 13

    private fun getDecimalTextViewAttributeSet(): AttributeSet? {
        return buildAttributeSet {
            addAttribute(R.attr.currency, currency)
            addAttribute(R.attr.maxDecimalDigit, maxDecimalDigit.toString())
            addAttribute(R.attr.maxInputLength, maxInputLength.toString())
        }
    }

    private fun getNumberKeyboardViewAttributeSet(): AttributeSet? {
        return buildAttributeSet {
            addAttribute(R.attr.decimalTextViewId, decimalTextView.id.toString())
        }
    }

    @Before
    fun setup() {
        activity.setTheme(R.style.Theme_MaterialComponents_Light_DarkActionBar)
        decimalTextView = DecimalTextView(activity, getDecimalTextViewAttributeSet())
        numberKeyboardView = NumberKeyboardView(activity, getNumberKeyboardViewAttributeSet())
        numberKeyboardView.setDecimalTextView(decimalTextView)
    }

    @Test
    fun `click a number button - number value should be added as decimal with thousand separator`() {
        val button1 = numberKeyboardView.findViewById<MaterialButton>(R.id.button1).apply {
            performClick()
        }
        decimalTextView.assertText(
            "$currency ${
                button1.text.toString().toDecimalWithMask(maxDecimalDigit)
            }"
        )
    }

    @Test
    fun `click on a group of number buttons - number values should be added as decimal with thousand separator`() {
        val button1 = numberKeyboardView.findViewById<MaterialButton>(R.id.button1).apply {
            performClick()
        }
        val button7 = numberKeyboardView.findViewById<MaterialButton>(R.id.button7).apply {
            performClick()
        }
        val button6 = numberKeyboardView.findViewById<MaterialButton>(R.id.button6).apply {
            performClick()
        }
        val button3 = numberKeyboardView.findViewById<MaterialButton>(R.id.button3).apply {
            performClick()
        }
        val buttonDot = numberKeyboardView.findViewById<MaterialButton>(R.id.buttonDot).apply {
            performClick()
        }
        val button2 = numberKeyboardView.findViewById<MaterialButton>(R.id.button2).apply {
            performClick()
        }

        val number =
            "${button1.text}${button7.text}${button6.text}${button3.text}${buttonDot.text}${button2.text}"
        decimalTextView.assertText(
            "$currency ${
                number.toDecimalWithMask(maxDecimalDigit)
            }"
        )
    }

    @Test
    fun `set number text - text should be converted to decimal number with thousand separator`() {
        val number = "5895248.56"
        decimalTextView.setText(number)

        val enteredNumber = number.toDecimal(maxDecimalDigit).crop(maxInputLength)

        decimalTextView.assertText(
            "$currency ${
                enteredNumber.toDecimalWithMask(maxDecimalDigit)
            }"
        )
    }

    @Test
    fun `remove text - remove the last character`() {
        val number = "4538568.54"
        decimalTextView.setText(number)

        val enteredNumber = number.toDecimal(maxDecimalDigit).crop(maxInputLength)

        numberKeyboardView.findViewById<MaterialButton>(R.id.buttonRemove).apply {
            performClick()
        }

        decimalTextView.assertText(
            "$currency ${
                enteredNumber.removeLast().toDecimalWithMask(maxDecimalDigit)
            }"
        )
    }

}