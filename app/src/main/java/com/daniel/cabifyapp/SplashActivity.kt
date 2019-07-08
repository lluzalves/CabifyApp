package com.daniel.cabifyapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private lateinit var animation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        container.setBackgroundResource(R.drawable.animation_splash_screen)
        animation = container.background as AnimationDrawable
    }

    override fun onResume() {
        super.onResume()
        animation.start()
        onAnimationProgress(50, animation)
    }

    private fun onAnimationProgress(period: Long, animationDrawable: AnimationDrawable) {
        Handler().run {
            return@run postDelayed({
                when {
                    animationDrawable.current !== animationDrawable.getFrame(animationDrawable.numberOfFrames - 1) -> onAnimationProgress(period, animationDrawable)
                    else -> {
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }, period)
        }
    }
}
