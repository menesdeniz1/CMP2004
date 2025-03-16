package com.example.a2004projecttry6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.a2004projecttry6.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var D1 : CardView ? = null
    private var D3 : CardView ? = null
    private var D5 : CardView ? = null
    private var D6 : CardView ? = null
    private var D7 : CardView ? = null
    private var D8 : CardView ? = null
    private var D9 : CardView ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        D1= findViewById(R.id.learncard1)
        D3= findViewById(R.id.learncard3)
        D5= findViewById(R.id.learncard5)
        D6= findViewById(R.id.learncard6)
        D7= findViewById(R.id.learncard7)
        D8= findViewById(R.id.learncard8)
        D9= findViewById(R.id.learncard9)

        D1?.setOnClickListener(this)
        D3?.setOnClickListener(this)
        D5?.setOnClickListener(this)
        D6?.setOnClickListener(this)
        D7?.setOnClickListener(this)
        D8?.setOnClickListener(this)
        D9?.setOnClickListener(this)

        val bottom_nav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottom_nav.selectedItemId = R.id.bottom_learn

        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_learn -> true
                R.id.bottom_play -> {
                    startActivity(Intent(applicationContext, PlayActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
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
            R.id.learncard1 -> Intent(this, LearnCalendarCardActivity::class.java)
            R.id.learncard3 -> Intent(this, LearnMonthsCardActivity::class.java)
            R.id.learncard5 -> Intent(this, LearnSpellingCardActivity::class.java)
            R.id.learncard6 -> Intent(this, LearnWeathersCardActivity::class.java)
            R.id.learncard7 -> Intent(this, LearnMultiplicationCardActivity::class.java)
            R.id.learncard8 -> Intent(this, LearnDirectionsCardActivity::class.java)
            R.id.learncard9 -> Intent(this, LearnClockCardActivity::class.java)
            else -> throw IllegalArgumentException("Unhandled view ID: ${v.id}")
        }
        startActivity(i)
    }
}




