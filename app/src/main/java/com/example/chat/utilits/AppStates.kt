package com.example.chat.utilits

/*Проверяет состояние активности пользователя пользователя*/
/*Добавили enum с состоянием пользователя и отправляем данные на сервер*/

enum class AppStates(val state: String) {
    ONLINE ("в сети"),
    OFFLINE ("был недавно"),
    TYPING ("печатает");

    companion object{
        fun updateState(appStates: AppStates){
            if (AUTH.currentUser != null) {
                REF_DATABASE_ROOD.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                    .setValue(appStates.state)
                    .addOnSuccessListener { USER.state = appStates.state }
                    .addOnFailureListener { showToast(it.message.toString()) }
            }
        }
    }
}