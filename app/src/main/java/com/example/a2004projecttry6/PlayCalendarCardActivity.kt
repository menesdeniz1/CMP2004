package com.example.a2004projecttry6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class PlayCalendarCardActivity : AppCompatActivity() {
    private lateinit var targetWordTextView: TextView
    private lateinit var wordEditText: EditText
    private lateinit var checkButton: Button

    private val targetWords = mapOf(
        "monday" to "sunday",
        "tuesday" to "monday",
        "wednesday" to "tuesday",
        "thursday" to "wednesday",
        "friday" to "thursday",
        "saturday" to "friday",
        "sunday" to "saturday"

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_calendar_activity_card)

        targetWordTextView = findViewById(R.id.target_word_text_view)
        wordEditText = findViewById(R.id.word_edit_text)
        checkButton = findViewById(R.id.check_button)

        checkButton.setOnClickListener { checkSpelling() }

        setRandomTargetWord()
    }

    private fun setRandomTargetWord() {
        val randomWord = targetWords.keys.random()
        val targetSpelling = targetWords[randomWord]
        targetWordTextView.text = "Target Days: $randomWord"

    }

    private fun checkSpelling() {
        val targetWord = targetWordTextView.text.toString().substringAfter("Target Days:").trim()
        val targetSpelling = targetWords[targetWord]
        val userSpelling = wordEditText.text.toString().trim()

        val targetSyllables = targetSpelling?.split(",")?.map { it.trim() }
        val userSyllables = userSpelling.split(",").map { it.trim() }

        if (userSyllables == targetSyllables) {
            showMessage("You find the correct day!")
        } else {
            showMessage("Incorrect entry. Try again.")
        }

        wordEditText.text.clear()
        setRandomTargetWord()
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
