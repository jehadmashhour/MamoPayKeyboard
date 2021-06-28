package com.example.mamopayproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.NumberKeyListener
import android.view.LayoutInflater
import android.widget.Toast
import com.example.keyboard.listeners.NumberKeyboardListener
import com.example.mamopayproject.databinding.ActivityMainBinding

/**
 * Project: Classera
 *
 * @author Jehad Abdalqader
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun setup(binding: ActivityMainBinding) {
        with(binding)
        {
            numberKeyboardView.setNumberKeyboardListener(object : NumberKeyboardListener {
                override fun onNumberButtonClicked(value: CharSequence) {
                    //Do your implementation
                }

                override fun onClearButtonClicked() {
                    //Do your implementation
                }
            })

        }
    }


}