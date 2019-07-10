package com.daniel.cabifyapp.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.daniel.cabifyapp.store.checkout.StoreCheckout

class CustomerTextWatcher(
    private val name: EditText,
    private val address: EditText,
    private val email: EditText,
    private val cellphone: EditText,
    private val errorMessage: String,
    private var storeCheckout: StoreCheckout
) : TextWatcher {


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (!name.text.isNullOrEmpty() && !email.text.isNullOrEmpty() && !address.text.isNullOrEmpty() && !cellphone.text.isNullOrEmpty()) {
            storeCheckout.allowCheckout()
            return
        }
        if (address.text.isNullOrEmpty()) {
            address.error = errorMessage

        } else {
            address.error = null
        }
        if (name.text.isNullOrEmpty()) {
            name.error = errorMessage
        } else {
            name.error = null
        }
        if (email.text.isNullOrEmpty()) {
            email.error = errorMessage
        } else {
            email.error = null
        }
        if (cellphone.text.isNullOrEmpty()) {
            cellphone.error = errorMessage
        } else {
            cellphone.error = null
        }
        storeCheckout.blockCheckout()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}