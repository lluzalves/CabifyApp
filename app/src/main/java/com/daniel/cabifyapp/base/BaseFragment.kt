package com.daniel.cabifyapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.daniel.cabifyapp.view.CabifySnackBar

abstract class BaseFragment : Fragment(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onLoading(loadingMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted() {

    }

    override fun onError(errorMessage: String) {
        view?.let { CabifySnackBar.make(it, errorMessage).show() }
    }

    override fun onError(throwable: Throwable) {
        view?.let { CabifySnackBar.make(it, throwable.localizedMessage).show() }
    }

    override fun onNextApplicationState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}