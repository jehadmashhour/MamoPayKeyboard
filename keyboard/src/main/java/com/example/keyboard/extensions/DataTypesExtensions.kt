package com.example.keyboard.extensions

import com.example.keyboard.uitls.Utils
import kotlin.math.absoluteValue

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */

fun CharSequence?.isNumber(): Boolean {
    return if (this.isNullOrEmpty()) false else this.all { Character.isDigit(it) }
}

fun String.removeLast(): String = substring(0, this.length - 1)

fun String.crop(maxLength: Int): String = if (length > maxLength) substring(0, maxLength) else this


fun String.toDecimal(maxDecimalDigit: Int): String {
    return this.takeIf { it.isNotEmpty() }?.let {
        val parsed = this.replace("[,]".toRegex(), "").replace("..", ".")
        return@let if (parsed.contains(".")) {
            Utils.formatDecimal(parsed, maxDecimalDigit)
        } else Utils.formatInteger(parsed)
    } ?: ""
}

fun String.toDecimalWithMask(maxDecimalDigit: Int): String {
    var div = 0
    val decimalPattern = StringBuilder()
    decimalPattern.append(this.toDecimal(maxDecimalDigit))

    if (this.contains(".")) {
        div = this.split(".")[1].length
    } else {
        decimalPattern.append(".")
    }

    var i = div
    while (div < maxDecimalDigit && i < maxDecimalDigit) {
        decimalPattern.append("0")
        i++
    }
    return decimalPattern.toString()
}


fun String.getDecimalMask(maxDecimalDigit: Int): String {
    var div = 0
    val decimalPattern = StringBuilder()

    if (this.isEmpty()) {
        decimalPattern.append("0")
    }

    if (this.contains(".")) {
        div = this.split(".")[1].length
    } else {
        decimalPattern.append(".")
    }

    var i = div
    while (div < maxDecimalDigit && i < maxDecimalDigit) {
        decimalPattern.append("0")
        i++
    }
    return decimalPattern.toString()
}



fun Int.isNotZero(): Boolean {
    return this.absoluteValue != 0
}

