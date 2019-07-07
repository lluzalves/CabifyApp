package com.daniel.cabifyapp

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity : AppCompatActivity() {

    private lateinit var animation : AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        container.setBackgroundResource(R.drawable.animation_splash_screen)
        animation = container.background as AnimationDrawable
    }

    override fun onResume() {
        super.onResume()
        animation.start()
        onAnimationProgress(50,animation)
    }

    private fun onAnimationProgress(period: Long, animationDrawable: AnimationDrawable) {
    Handler().run {
            postDelayed({
                if (animationDrawable.current !== animationDrawable.getFrame(animationDrawable.numberOfFrames - 1))
                    onAnimationProgress(period, animationDrawable)
                else
                    finish()
            }, period)
        }
    }
}
