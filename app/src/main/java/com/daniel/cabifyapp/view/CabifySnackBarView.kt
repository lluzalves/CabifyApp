package com.daniel.cabifyapp.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.daniel.cabifyapp.R
import com.google.android.material.snackbar.ContentViewCallback
import kotlinx.android.synthetic.main.view_cabify_snack_bar_notification.view.*

class CabifySnackBarView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    styleAttributes: Int = 0
) : ConstraintLayout(context, attributes, styleAttributes), ContentViewCallback {


    init {
        View.inflate(context, R.layout.view_cabify_snack_bar_notification, this)
        clipToPadding = false
    }

    fun setMessage(message: String) {
        notification_message.text = message
    }

    override fun animateContentIn(p0: Int, p1: Int) {
        val scaleX = ObjectAnimator.ofFloat(cabify_logo, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(cabify_logo, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            duration = 500
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(p0: Int, p1: Int) {
        val scaleX = ObjectAnimator.ofFloat(cabify_logo, View.SCALE_X, 1f, 0f)
        val scaleY = ObjectAnimator.ofFloat(cabify_logo, View.SCALE_Y, 1f, 0f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            duration = 1000
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }
}