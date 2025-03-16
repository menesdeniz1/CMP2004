package com.example.a2004projecttry6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class PlaySpellingCardActivity : AppCompatActivity() {
    private lateinit var targetWordTextView: TextView
    private lateinit var wordEditText: EditText
    private lateinit var checkButton: Button

    private val targetWords = mapOf(
        "merhaba" to "mer-ha-ba",
        "masa" to "ma-sa",
        "kitap" to "ki-tap",
        "defter" to "def-ter",
        "olmak" to "ol-mak",
        "mercimek" to "mer-ci-mek",
        "tiyatro" to "ti-yat-ro",
        "fasulye" to "fa-sul-ye"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_spelling_activity_card)

        targetWordTextView = findViewById(R.id.target_word_text_view)
        wordEditText = findViewById(R.id.word_edit_text)
        checkButton = findViewById(R.id.check_button)

        checkButton.setOnClickListener { checkSpelling() }

        setRandomTargetWord()
    }

    private fun setRandomTargetWord() {
        val randomWord = targetWords.keys.random()
        val targetSpelling = targetWords[randomWord]
        targetWordTextView.text = "Target Word: $randomWord"

    }

    private fun checkSpelling() {
        val targetWord = targetWordTextView.text.toString().substringAfter("Target Word:").trim()
        val targetSpelling = targetWords[targetWord]
        val userSpelling = wordEditText.text.toString().trim()

        val targetSyllables = targetSpelling?.split(",")?.map { it.trim() }
        val userSyllables = userSpelling.split(",").map { it.trim() }

        if (userSyllables == targetSyllables) {
            showMessage("Correct spelling!")
        } else {
            showMessage("Incorrect spelling. Try again.")
        }

        wordEditText.text.clear()
        setRandomTargetWord()
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
