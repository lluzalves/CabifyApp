package com.daniel.cabifyapp.commons

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.daniel.cabifyapp.R

class ProductDrawables {
    companion object {
        fun onProductCodeSetBackground(code: String, view: ImageView) {
            when (code) {
                "VOUCHER" -> {
                    view.setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_cabify_logo_color_rgb
                        )
                    )
                }
                "TSHIRT" -> {
                    view.setImageDrawable(ContextCompat.getDrawable(view.context, R.mipmap.ic_shirts))
                }
                "MUG" -> {
                    view.setImageDrawable(ContextCompat.getDrawable(view.context, R.mipmap.ic_mugs))
                }
                else -> {
                    view.setImageDrawable(
                        ContextCompat.getDrawable(view.context, android.R.drawable.stat_notify_error)
                    )
                }

            }
        }

    }

}
