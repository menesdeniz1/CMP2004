package com.example.a2004projecttry6

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class PlayMatchingCardActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var previousImageView: ImageView
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_matching_activity_card)

        gridLayout = findViewById(R.id.gridLayout)
        previousImageView = findViewById(R.id.image1)
    }

    fun onImageClick(view: View) {
        val currentImageView = view as ImageView

        // Check if the clicked image is already invisible
        if (currentImageView.visibility == View.INVISIBLE) {
            return
        }

        // Check if the clicked image is the same as the previous one
        if (currentImageView.tag == previousImageView.tag) {
            // Make both images invisible
            currentImageView.visibility = View.INVISIBLE
            previousImageView.visibility = View.INVISIBLE
            score += 10

            // Check if all images are now invisible
            if (areAllImagesInvisible()) {
                showCongratsToast(score)
            }
        }

        // Update the previous image view reference
        previousImageView = currentImageView
    }

    private fun areAllImagesInvisible(): Boolean {
        for (i in 0 until gridLayout.childCount) {
            val child = gridLayout.getChildAt(i)
            if (child is ImageView && child.visibility != View.INVISIBLE) {
                return false
            }
        }
        return true
    }

    private fun showCongratsToast(score: Int) {
        val message = "Congratulations! All images are invisible. Your score is $score."
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
