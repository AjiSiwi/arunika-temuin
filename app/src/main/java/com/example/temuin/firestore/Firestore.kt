package com.example.temuin.firestore

import android.app.Activity
import android.util.Log
import com.example.temuin.data.UserEntity
import com.example.temuin.ui.login.LoginActivity
import com.example.temuin.ui.register.RegisterActivity
import com.example.temuin.utils.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestore {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userEntityInfo: UserEntity){
        mFireStore.collection(Constant.USERS)
            .document(userEntityInfo.uId)
            .set(userEntityInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener {e ->
                Log.e(activity.javaClass.simpleName, "Gagal Meregistrasi User",e)
            }
    }

    fun getCurrentUserID():String{
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

//    fun getUserDetails(activity: Activity){
//        mFireStore.collection(Constant.USERS)
//            .document(getCurrentUserID())
//            .get()
//            .addOnSuccessListener { document ->
//                Log.i(activity.javaClass.simpleName, document.toString())
//
//                val user = document.toObject(UserEntity::class.java)
//
//                when(activity){
//                    is LoginActivity ->{
//                        activity.userLoggedInSuccess(user)
//                    }
//                }
//
//            }
//            .addOnFailureListener {
//                Log.e(activity.javaClass.simpleName, "Gagal Meregistrasi User")
//            }
//    }
}