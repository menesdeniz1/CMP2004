package com.example.a2004projecttry6

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scoreLabel: TextView = findViewById(R.id.scoreLabel)
        val highScoreLabel: TextView = findViewById(R.id.highScoreLabel)

        val score = intent.getIntExtra("SCORE", 0)
        scoreLabel.text = getString(R.string.result_score, score)

        // High Score
        val sharedPreferences: SharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val highScore = sharedPreferences.getInt("HIGH_SCORE", 0)

        if (score > highScore) {
            // Update HighScore
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("HIGH_SCORE", score)
            editor.apply()

            highScoreLabel.text = getString(R.string.high_score, score)
        } else {
            highScoreLabel.text = getString(R.string.high_score, highScore)
        }
    }

    fun tryAgain(view: View) {
        startActivity(Intent(applicationContext, PlayCatchGameActivity::class.java))
    }

    override fun onBackPressed() {
        // Disable the back button
    }
}