package com.example.a2004projecttry6

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PlayCatchGameActivity : AppCompatActivity() {
    // Elements
    private lateinit var scoreLabel: TextView
    private lateinit var startLabel: TextView
    private lateinit var box: ImageView
    private lateinit var orange: ImageView
    private lateinit var pink: ImageView
    private lateinit var black: ImageView

    // Size
    private var screenWidth = 0
    private var frameHeight = 0
    private var boxSize = 0

    // Position
    private var boxY = 0f
    private var orangeX = 0f
    private var orangeY = 0f
    private var pinkX = 0f
    private var pinkY = 0f
    private var blackX = 0f
    private var blackY = 0f

    // Speed
    private var boxSpeed = 0
    private var orangeSpeed = 0
    private var pinkSpeed = 0
    private var blackSpeed = 0

    // Score
    private var score = 0

    // Timer
    private var timer: Timer? = null
    private val handler = Handler()

    // Status
    private var actionFlg = false
    private var startFlg = false

    // SoundPlayer
    private var soundPlayer: SoundPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_catch_game)
        soundPlayer = SoundPlayer(this)
        scoreLabel = findViewById(R.id.scoreLabel)
        startLabel = findViewById(R.id.startLabel)
        box = findViewById(R.id.box)
        orange = findViewById(R.id.orange)
        pink = findViewById(R.id.pink)
        black = findViewById(R.id.black)

        // Screen Size
        val windowManager = windowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        val screenHeight = size.y

        // Nexus 4 width:768 height:1184
        // Speed box:20, orange:12, pink:20, black:16
        boxSpeed = (screenHeight / 60.0f).toInt() // 1184 / 60 = 19.733... => 20
        orangeSpeed = (screenWidth / 60.0f).toInt() // 768 / 60 = 12.8 => 13
        pinkSpeed = (screenWidth / 56.0f).toInt() // 768 / 36 = 21.333 => 21
        blackSpeed = (screenWidth / 50.0f).toInt() // 768 / 45 = 17.06... => 17

        // Initial Positions
        orange.x = -80.0f
        orange.y = -80.0f
        pink.x = -80.0f
        pink.y = -80.0f
        black.x = -80.0f
        black.y = -80.0f

        scoreLabel.text = getString(R.string.score, score)
    }

    private fun changePos() {
        hitCheck()

        // Orange
        orangeX -= orangeSpeed.toFloat()
        if (orangeX < 0) {
            orangeX = (screenWidth + 20).toFloat()
            orangeY = (Math.random() * (frameHeight - orange.height)).toFloat()
        }
        orange.x = orangeX
        orange.y = orangeY

        // Black
        blackX -= blackSpeed.toFloat()
        if (blackX < 0) {
            blackX = (screenWidth + 10).toFloat()
            blackY = (Math.random() * (frameHeight - black.height)).toFloat()
        }
        black.x = blackX
        black.y = blackY

        // Pink
        pinkX -= pinkSpeed.toFloat()
        if (pinkX < 0) {
            pinkX = (screenWidth + 5000).toFloat()
            pinkY = (Math.random() * (frameHeight - pink.height)).toFloat()
        }
        pink.x = pinkX
        pink.y = pinkY

        // Box
        if (actionFlg) {
            // Touching
            boxY -= boxSpeed.toFloat()
        } else {
            // Releasing
            boxY += boxSpeed.toFloat()
        }
        if (boxY < 0) boxY = 0f
        if (boxY > frameHeight - boxSize) boxY = (frameHeight - boxSize).toFloat()
        box.y = boxY

        scoreLabel.text = getString(R.string.score, score)
    }

    private fun hitCheck() {
        // Orange
        val orangeCenterX = orangeX + orange.width / 2.0f
        val orangeCenterY = orangeY + orange.height / 2.0f
        if (0 <= orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {
            orangeX = -100.0f
            score += 10
            soundPlayer?.playHitSound()
        }

        // Pink
        val pinkCenterX = pinkX + pink.width / 2.0f
        val pinkCenterY = pinkY + pink.height / 2.0f
        if (0 <= pinkCenterX && pinkCenterX <= boxSize && boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {
            pinkX = -100.0f
            score += 30
            soundPlayer?.playHitSound()
        }

        // Black
        val blackCenterX = blackX + black.width / 2.0f
        val blackCenterY = blackY + black.height / 2.0f
        if (0 <= blackCenterX && blackCenterX <= boxSize && boxY <= blackCenterY && blackCenterY <= boxY + boxSize) {
            soundPlayer?.playOverSound()

            // Game Over!!
            timer?.cancel()
            timer = null

            // Show ResultActivity
            val intent = Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!startFlg) {
            startFlg = true

            // FrameHeight
            val frameLayout = findViewById<FrameLayout>(R.id.frame)
            frameHeight = frameLayout.height

            // Box
            boxY = box.y
            boxSize = box.height
            startLabel.visibility = View.GONE

            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler.post { changePos() }
                }
            }, 0, 20)
        } else {
            if (event.action == MotionEvent.ACTION_DOWN) {
                actionFlg = true
            } else if (event.action == MotionEvent.ACTION_UP) {
                actionFlg = false
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onBackPressed() {
        // Disable the back button
    }
}
