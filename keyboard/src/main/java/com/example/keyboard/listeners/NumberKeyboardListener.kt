package com.example.keyboard.listeners

/**
 * Project: MamoPay
 *
 * @author Jehad Abdalqader
 */
interface NumberKeyboardListener {

    fun onNumberButtonClicked(value:CharSequence)

    fun onClearButtonClicked()
}