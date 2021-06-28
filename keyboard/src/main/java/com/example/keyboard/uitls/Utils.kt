package com.example.keyboard.uitls

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */
object Utils {


    fun formatInteger(str: String): String? {
        val parsed = BigDecimal(str)
        val integerFormatter = DecimalFormat("#,###.###", DecimalFormatSymbols(Locale.US))
        return integerFormatter.format(parsed)
    }

    fun formatDecimal(str: String, maxDecimalDigit: Int): String? {
        return str.takeIf { it != "." }?.let {
            val parsed = BigDecimal(str)
            val formatter = DecimalFormat(
                "#,##0." + getDecimalPattern(str, maxDecimalDigit),
                DecimalFormatSymbols(Locale.US)
            )
            formatter.roundingMode = RoundingMode.DOWN
            return formatter.format(parsed)
        } ?: "0."
    }

    private fun getDecimalPattern(str: String, maxDecimalDigit: Int): String {
        val decimalCount = str.length - str.indexOf(".") - 1
        val decimalPattern = StringBuilder()
        var i = 0
        while (i < decimalCount && i < maxDecimalDigit) {
            decimalPattern.append("0")
            i++
        }
        return decimalPattern.toString()
    }



}