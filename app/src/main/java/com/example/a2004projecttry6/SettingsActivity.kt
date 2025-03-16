package com.example.a2004projecttry6

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a2004projecttry6.databinding.ActivitySettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Locale

class SettingsActivity : AppCompatActivity() {

    private lateinit var  auth: FirebaseAuth
    private lateinit var binding:ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= Firebase.auth
        val changeLanguageButton = findViewById<Button>(R.id.change_language_button)
        val currentLanguage = resources.configuration.locale.language
        val newLanguage = if (currentLanguage == "tr") "en" else "tr"
        changeLanguageButton.setOnClickListener {
            val locale = Locale(newLanguage) // Türkçe dilini kullanmak için
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)
            recreate() // Yeniden başlatma işlemi
        }



        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            val intent=Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.chatGPTbutton.setOnClickListener {
            val intent=Intent(this,ChatActivity::class.java)
            startActivity(intent)
            finish()
        }


        val bottom_nav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottom_nav.selectedItemId = R.id.bottom_settings

        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_learn -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_play -> {
                    startActivity(Intent(applicationContext, PlayActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_settings -> true
                else -> false
            }
        }
    }
}