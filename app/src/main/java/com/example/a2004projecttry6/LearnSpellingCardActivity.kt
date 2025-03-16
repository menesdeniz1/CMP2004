package com.example.a2004projecttry6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class LearnSpellingCardActivity : AppCompatActivity() {

    private lateinit var textViews: List<TextView>
    private var currentTextViewIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_spelling_activity_card)

        textViews = listOf(
            findViewById(R.id.text_view1),
            findViewById(R.id.text_view2),
            findViewById(R.id.text_view3),
            findViewById(R.id.text_view4),
            findViewById(R.id.text_view5),
            findViewById(R.id.text_view6)
        )

        for (textView in textViews) {
            textView.setOnClickListener { showNextTextView() }
        }
    }

    private fun showNextTextView() {
        currentTextViewIndex = (currentTextViewIndex + 1) % textViews.size
        textViews[currentTextViewIndex].visibility = View.VISIBLE
    }
}

