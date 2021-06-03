package com.example.temuin.firestore

import android.util.Log
import com.example.temuin.model.User
import com.example.temuin.ui.register.RegisterActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestore {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){
        mFireStore.collection("users")
            .document(userInfo.uId)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "Gagal Meregistrasi User")
            }
    }
}