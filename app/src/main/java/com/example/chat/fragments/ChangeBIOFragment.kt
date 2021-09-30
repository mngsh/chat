package com.example.chat.ui.fragments

import com.example.chat.R
import com.example.chat.utilits.*
import kotlinx.android.synthetic.main.fragment_change_b_i_o.*

class ChangeBIOFragment : BaseChangeFragment(R.layout.fragment_change_b_i_o) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)

    }

    override fun change() {
        super.change()
        val newBIO = settings_input_bio.text.toString()
        REF_DATABASE_ROOD.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBIO)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    USER.bio = newBIO
                    fragmentManager?.popBackStack()
                }
            }
    }
}