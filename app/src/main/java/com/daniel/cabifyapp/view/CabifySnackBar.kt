package com.daniel.cabifyapp.view

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.daniel.cabifyapp.R
import com.google.android.material.snackbar.BaseTransientBottomBar

class CabifySnackBar(parent: ViewGroup, content: CabifySnackBarView) :
    BaseTransientBottomBar<CabifySnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(view: View, message : String): CabifySnackBar {

            val parent: ViewGroup =
                view.findParent() ?: throw IllegalArgumentException(view.context.getString(R.string.error_view))
            val inflater = from(view.context)
            val content = inflater.inflate(R.layout.layout_cabify_warning,parent,false)
            val snackBarView: CabifySnackBarView = content as CabifySnackBarView
            snackBarView.setMessage(message)

            return CabifySnackBar(
                parent,
                snackBarView
            )
        }

    }
}
