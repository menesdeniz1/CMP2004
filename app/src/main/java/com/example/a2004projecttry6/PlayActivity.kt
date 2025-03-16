package com.example.a2004projecttry6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.a2004projecttry6.databinding.ActivityPlayBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPlayBinding

    private var D1 : CardView? = null
    private var D2 : CardView? = null
    private var D3 : CardView? = null
    private var D4 : CardView? = null
    private var D5 : CardView? = null
    private var D6 : CardView? = null
    private var D7 : CardView? = null
    private var D8 : CardView? = null
    private var D9 : CardView? = null
    private var D10 : CardView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPlayBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_play)

        D1= findViewById(R.id.playcard1)
        D2= findViewById(R.id.playcard2)
        D3= findViewById(R.id.playcard3)
        D4= findViewById(R.id.playcard4)
        D5= findViewById(R.id.playcard5)
        D6= findViewById(R.id.playcard6)
        D7= findViewById(R.id.playcard7)
        D8= findViewById(R.id.playcard8)
        D9= findViewById(R.id.playcard9)
        D10= findViewById(R.id.playcard10)

        D1?.setOnClickListener(this)
        D2?.setOnClickListener(this)
        D3?.setOnClickListener(this)
        D4?.setOnClickListener(this)
        D5?.setOnClickListener(this)
        D6?.setOnClickListener(this)
        D7?.setOnClickListener(this)
        D8?.setOnClickListener(this)
        D9?.setOnClickListener(this)
        D10?.setOnClickListener(this)

        val bottom_nav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottom_nav.selectedItemId = R.id.bottom_play

        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_learn -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_play -> true
                R.id.bottom_settings -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    override fun onClick(v: View) {
        val i:Intent = when (v.id) {
            R.id.playcard1 -> Intent(this, PlayCalendarCardActivity::class.java)
            R.id.playcard2 -> Intent(this, PlayRememberNumsCardActivity::class.java)
            R.id.playcard3 -> Intent(this, PlayMonthsCardActivity::class.java)
            R.id.playcard4 -> Intent(this, PlayMatchingCardActivity::class.java)
            R.id.playcard5 -> Intent(this, PlaySpellingCardActivity::class.java)
            R.id.playcard6 -> Intent(this, PlaySnakeCardActivity::class.java)
            R.id.playcard7 -> Intent(this, PlayMultiplicationCardActivity::class.java)
            R.id.playcard8 -> Intent(this, PlayRememberNumsBackwardCardActivity::class.java)
            R.id.playcard9 -> Intent(this, PlayFollowBallCardActivity::class.java)
            R.id.playcard10 -> Intent(this, PlayCatchGameActivity::class.java)
            else -> throw IllegalArgumentException("Unhandled view ID: ${v.id}")
        }
        startActivity(i)
    }
}