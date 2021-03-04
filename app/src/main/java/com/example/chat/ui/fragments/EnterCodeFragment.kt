package com.example.chat.ui.fragments

import androidx.fragment.app.Fragment
import com.example.chat.MainActivity
import com.example.chat.R
import com.example.chat.activities.RegisterActivity
import com.example.chat.utilits.AUTH
import com.example.chat.utilits.AppTextWatcher
import com.example.chat.utilits.replaceActivity
import com.example.chat.utilits.showToast
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val mPhoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string: String = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code: String = register_input_code.text.toString()
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}