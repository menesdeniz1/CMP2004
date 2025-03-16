package com.example.a2004projecttry6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PlayMultiplicationCardActivity : AppCompatActivity() {
    private lateinit var multiplicationButton: Button
    private lateinit var problemTextView: EditText
    private lateinit var resultEditText: EditText

    private val random = Random()
    private var firstNumber: Int = 0
    private var secondNumber: Int = 0
    private var result: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_multiplication_card)

        multiplicationButton = findViewById(R.id.multiplication_button)
        problemTextView = findViewById(R.id.problem_text_view)
        resultEditText = findViewById(R.id.result_edit_text)

        multiplicationButton.setOnClickListener { showRandomMultiplicationProblem() }
    }

    private fun showRandomMultiplicationProblem() {
        firstNumber = random.nextInt(10) + 1 // Generate a random number between 1 and 10
        secondNumber = random.nextInt(10) + 1
        result = firstNumber * secondNumber

        problemTextView.setText("$firstNumber * $secondNumber = ")
        resultEditText.setText("")
    }

    fun checkResult(view: View) {
        val enteredResult = resultEditText.text.toString().toIntOrNull()

        if (enteredResult != null) {
            if (enteredResult == result) {
                problemTextView.setText("Correct!")
            } else {
                problemTextView.setText("Incorrect. Try again.")
            }
        } else {
            problemTextView.setText("Please enter a valid number.")
        }
    }
}