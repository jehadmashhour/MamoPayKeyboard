package com.example.keyboard.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.keyboard.listeners.NumberKeyboardListener
import com.example.keyboard.R
import com.example.keyboard.extensions.inflate
import com.example.keyboard.extensions.isNotZero
import com.google.android.material.button.MaterialButton

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */
class NumberKeyboardView : ConstraintLayout {

    private var numberKeyboardListener: NumberKeyboardListener? = null
    private var decimalTextView: DecimalTextView? = null
    private var decimalTextViewId: Int = NO_ID

    constructor(context: Context) : super(context) {
        setup(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        inflateView()
        setButtonsListeners()
        setupAttributes(attrs)
    }

    private fun inflateView() {
        inflate(R.layout.keyboard_layout)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                it, R.styleable.NumberKeyboardView,
                0, 0
            )


            decimalTextViewId =
                typedArray.getResourceId(R.styleable.NumberKeyboardView_decimalTextViewId, NO_ID)

            typedArray.recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        findViews()
    }

    private fun findViews() {
        if (decimalTextViewId.isNotZero()) {
            decimalTextView = rootView.findViewById(decimalTextViewId)
        }
    }

    fun setDecimalTextView(decimalTextView: DecimalTextView) {
        this.decimalTextView = decimalTextView

    }

    fun setNumberKeyboardListener(numberKeyboardListener: NumberKeyboardListener) {
        this.numberKeyboardListener = numberKeyboardListener
    }

    private fun setButtonsListeners() {
        val parentView = getChildAt(0) as ViewGroup
        for (index in 0 until parentView.childCount) {
            val childView = parentView.getChildAt(index)
            childView.setOnClickListener { v ->
                onButtonClicked(v as MaterialButton)
            }
        }
    }

    private fun onButtonClicked(materialButton: MaterialButton) {
        if (materialButton.text.isNotEmpty()) {
            onNumberButtonClicked(materialButton)
        } else {
            onClearButtonClicked()
        }
    }

    private fun onNumberButtonClicked(materialButton: MaterialButton) {
        this.decimalTextView?.addText(materialButton.text.toString())
        numberKeyboardListener?.onNumberButtonClicked(materialButton.text)
    }

    private fun onClearButtonClicked() {
        this.decimalTextView?.removeText()
        numberKeyboardListener?.onClearButtonClicked()
    }

    companion object {
        private const val NO_ID = 0
    }
}