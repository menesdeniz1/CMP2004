package com.example.a2004projecttry6
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build

class SoundPlayer(context: Context?) {
    private var soundPool: SoundPool? = null
    private var hitSound: Int = 0
    private var overSound: Int = 0

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // SoundPool is deprecated in API level 21.(Lollipop)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            soundPool = SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build()
        } else {
            soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        }
        hitSound = soundPool!!.load(context, R.raw.hit, 1)
        overSound = soundPool!!.load(context, R.raw.over, 1)
    }

    fun playHitSound() {
        soundPool?.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun playOverSound() {
        soundPool?.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }
}