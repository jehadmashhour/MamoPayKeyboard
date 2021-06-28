package com.example.keyboard.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.keyboard.R
import com.example.keyboard.extensions.*

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */
class DecimalTextView : AppCompatTextView {

    var enteredNumber: String = DEFAULT_AMOUNT
        private set
    private var currency: String? = DEFAULT_CURRENCY
    private var amountDimmingColor: Int =
        ContextCompat.getColor(context, DEFAULT_AMOUNT_DIMMING_COLOR)
    private var amountHighlightColor: Int =
        ContextCompat.getColor(context, DEFAULT_AMOUNT_HIGH_LIGHT_COLOR)
    private var maxDecimalDigit = DEFAULT_MAX_DECIMAL_DIGIT
    private var maxInputLength = DEFAULT_MAX_INPUT_LENGTH


    constructor(context: Context) : super(context) {
        setup(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup(attrs)
    }


    private fun setup(attrs: AttributeSet?) {
        setupAttributes(attrs)
        setText(text.toString())
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                it, R.styleable.DecimalTextView,
                0, R.style.DecimalTextViewDefaultStyle
            )
            currency = typedArray.getString(R.styleable.DecimalTextView_currency)
            amountDimmingColor = typedArray.getColor(
                R.styleable.DecimalTextView_amountDimmingColor,
                DEFAULT_AMOUNT_DIMMING_COLOR
            )
            amountHighlightColor = typedArray.getColor(
                R.styleable.DecimalTextView_amountHighlightColor,
                DEFAULT_AMOUNT_HIGH_LIGHT_COLOR
            )
            maxDecimalDigit = typedArray.getInt(
                R.styleable.DecimalTextView_maxDecimalDigit,
                DEFAULT_MAX_DECIMAL_DIGIT
            )
            maxInputLength = typedArray.getInt(
                R.styleable.DecimalTextView_maxInputLength,
                DEFAULT_MAX_INPUT_LENGTH
            )

            typedArray.recycle()
        }
    }

    fun addText(number: String) {
        if (!(number == "." && enteredNumber.contains("."))) {
            val decimalNumber = "$enteredNumber${number}".toDecimal(maxDecimalDigit)
            if (canAdd(decimalNumber)) {
                enteredNumber = decimalNumber
                showText()
            }

        }
    }

    fun setText(number: String) {
        val decimalNumber = getCroppedNumber(number)
        if (canAdd(decimalNumber)) {
            enteredNumber = decimalNumber
            showText()
        }
    }

    private fun getCroppedNumber(number: String): String {
        val decimalNumber = number.toDecimal(maxDecimalDigit)
        return decimalNumber.crop(maxInputLength)
    }


    fun removeText() {
        if (enteredNumber.isNotEmpty()) {
            enteredNumber = enteredNumber.removeLast().toDecimal(maxDecimalDigit)
            showText()
        }
    }


    fun clearText() {
        text = ""
    }


    private fun canAdd(number: String): Boolean {
        if (number.contains("."))
            return true
        return number.length <= maxInputLength
    }

    private fun showText() {
        clearText()
        currency?.let {
            append(
                "$it ",
                if (enteredNumber.isEmpty()) amountDimmingColor else amountHighlightColor
            )
        }
        append(enteredNumber, amountHighlightColor)
        append(enteredNumber.getDecimalMask(maxDecimalDigit), amountDimmingColor)
    }


    companion object {
        const val DEFAULT_AMOUNT = ""
        const val DEFAULT_CURRENCY = ""
        const val DEFAULT_MAX_DECIMAL_DIGIT = 1
        const val DEFAULT_MAX_INPUT_LENGTH = 13
        val DEFAULT_AMOUNT_DIMMING_COLOR = R.color.colorGray
        val DEFAULT_AMOUNT_HIGH_LIGHT_COLOR = R.color.colorBlack
    }
}