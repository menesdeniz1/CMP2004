package com.example.a2004projecttry6

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayFollowBallCardActivity : AppCompatActivity() {

    private lateinit var ballImageView: ImageView
    private lateinit var valueAnimator: ValueAnimator
    private lateinit var scoreTextView: TextView
    private var scoreCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_follow_ball_card)

        ballImageView = findViewById(R.id.ballImageView)
        scoreTextView = findViewById(R.id.score_text_view)

        // Start the ball movement animation
        startBallAnimation()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y

        if (event.action == MotionEvent.ACTION_DOWN) {
            // Check if the touch event occurred within the bounds of the ballImageView
            val rect = Rect()
            ballImageView.getGlobalVisibleRect(rect)

            if (rect.contains(touchX.toInt(), touchY.toInt())) {
                // User touched the ball, increment the score counter

                scoreCounter++
                updateScoreCounter()
                scoreTextView.setText("Score: $scoreCounter")

                // Example: Show a toast message with the updated score
                Toast.makeText(this, "Succesfull touch!", Toast.LENGTH_SHORT).show()

            }
        }

        return super.onTouchEvent(event)
    }

    private var resetY = 0f
    private val offsetYIncrement = 100f

    private fun startBallAnimation() {
        val screenWidth = resources.displayMetrics.widthPixels

        val ballHeight = ballImageView.height.toFloat()
        resetY += offsetYIncrement

        valueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 5000 // Set the duration of the animation (in milliseconds)
            interpolator = LinearInterpolator()
            repeatCount = 0 // Do not repeat the animation

            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float

                // Calculate the X position based on the progress and screen width
                val newX = (screenWidth - ballImageView.width) * progress

                ballImageView.translationX = newX
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    // Animation start callback
                }

                override fun onAnimationEnd(animation: Animator) {
                    // Animation end callback
                    ballImageView.translationY = resetY
                    startBallAnimation() // Start the animation again
                }

                override fun onAnimationCancel(animation: Animator) {
                    // Animation cancellation callback
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // Animation repeat callback, you can implement any logic here
                    // Example: Change the direction of the ball movement
                }
            })
        }

        valueAnimator.start()
    }





    private fun updateScoreCounter() {
        // Update the UI to display the current score
        // Example: You can set a TextView with the score value
    }

    override fun onDestroy() {
        super.onDestroy()
        valueAnimator.cancel()
    }
}