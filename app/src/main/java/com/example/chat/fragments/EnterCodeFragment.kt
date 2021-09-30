package com.example.chat.ui.fragments

import androidx.fragment.app.Fragment
import com.example.chat.MainActivity
import com.example.chat.R
import com.example.chat.activities.RegisterActivity
import com.example.chat.utilits.*
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val PhoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = PhoneNumber
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
                val uid: String = AUTH.currentUser?.uid.toString()

                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = PhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOD.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener {task2 ->
                        if (task2.isSuccessful){
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else {
                            showToast(task2.exception?.message.toString())
                        }
                    }

                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}