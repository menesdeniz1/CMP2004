package com.example.a2004projecttry6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayRememberNumsBackwardCardActivity : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var digitTextView: TextView
    private lateinit var userInputEditText: EditText
    private lateinit var checkButton: Button

    private val digitsList = mutableListOf<Int>()
    private var currentDigitIndex = 0
    private var isShowingDigits = false

    private val handler = Handler(Looper.getMainLooper())
    private val showDigitRunnable = object : Runnable {
        override fun run() {
            if (currentDigitIndex < digitsList.size) {
                digitTextView.text = digitsList[currentDigitIndex].toString()
                currentDigitIndex++
                handler.postDelayed(this, 1000) // Show each digit for 1 second
            } else {
                digitTextView.text = ""
                isShowingDigits = false
                currentDigitIndex = 0 // Reset the digit index for the user input phase
                digitTextView.setOnClickListener(null) // Disable further clicks
                checkButton.visibility = View.VISIBLE // Show the Check button for input validation
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_remember_nums_backward_card)

        startButton = findViewById(R.id.start_button)
        digitTextView = findViewById(R.id.digit_text_view)
        userInputEditText = findViewById(R.id.user_input_edit_text)
        checkButton = findViewById(R.id.check_button)

        startButton.setOnClickListener { startGame() }
        checkButton.setOnClickListener { checkInput() }
    }

    private fun startGame() {
        digitsList.clear()
        currentDigitIndex = 0
        isShowingDigits = true
        generateDigits(5) // Generate 5 random digits in reverse order

        showNextDigit()
    }


    private fun generateDigits(count: Int) {
        digitsList.clear()
        val random = java.util.Random()
        for (i in count downTo 1) {
            val digit = random.nextInt(10) // Generate a random digit from 0 to 9
            digitsList.add(digit)
        }
    }


    private fun showNextDigit() {
        if (isShowingDigits) {
            handler.postDelayed(showDigitRunnable, 1000)
        }
    }

    private fun checkInput() {
        val userInput = userInputEditText.text.toString().trim()
        if (userInput.isNotEmpty()) {
            val userDigits = userInput.toCharArray().map { it.toString().toInt() }.reversed()
            if (userDigits == digitsList) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }
            userInputEditText.text.clear()
            currentDigitIndex = 0 // Reset the digit index
            digitTextView.text = "" // Clear the text view
            checkButton.visibility = View.GONE // Hide the Check button
        } else {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
        }
    }

}
