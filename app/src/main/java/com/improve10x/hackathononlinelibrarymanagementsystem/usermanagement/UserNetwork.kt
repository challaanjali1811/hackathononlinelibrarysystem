package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity

class UserNetwork private constructor() {
    var currentUser : UserInf? = null
    companion object {

        @Volatile
        private var instance: UserNetwork? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserNetwork().also { instance = it }
            }
    }
    fun getUsers(onGetUsersDataListener: OnGetUsersDataListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnSuccessListener {
                val users = it.documents?.mapNotNull { it.toObject(UserInf::class.java) }
                if (users != null) {
                    onGetUsersDataListener.onUsersReceived(users)
                    Log.d("Users", Gson().toJson(users))
                } else {
                    Log.d("Users", "Users is null");
                }
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
                onGetUsersDataListener.onFailedToReceiveUsers(it)
            }
    }

    fun getUser(userId : String?, role : String?, onGetUserListener: OnGetUserListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(UserInf::class.java)
                if (user != null) {
                    onGetUserListener.onUserReceived(user)
                    Log.d("Current User", Gson().toJson(user))
                    Log.d("Current User Role", role!!)
                } else {
                    Log.d("User", "User is null");
                }
                currentUser = user
            }
            .addOnFailureListener {
                onGetUserListener.onFailedToReceiveUser(it)
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }

    fun getUser(userId : String?) {
        val db = FirebaseFirestore.getInstance()
        if (userId != null) {
             db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener {
                    val user = it.toObject(UserInf::class.java)
                    if (user != null) {
                        Log.d("Seller : ", Gson().toJson(user))
                        PaymentActivity.seller = user
                    } else {
                        Log.d("Seller", "User is null");
                    }

                }
                .addOnFailureListener {
                    Log.e(this.javaClass.simpleName, it.message, it)
                }
        }
    }

    fun addUser(user: UserInf) {
        val db = FirebaseFirestore.getInstance()
        user.id = db.collection("users").document().id
        db.collection("users")
            .document(user.id!!)
            .set(user)
            .addOnSuccessListener {
                Log.d("Users", "User added into list")
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }

    fun updateUser(user: UserInf) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document(user.id ?: "")
            .set(user)
            .addOnSuccessListener {
                Log.d("Users", "User details updated")
            }.addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }

    fun deleteUser(user: UserInf) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document(user.id!!)
            .delete()
            .addOnSuccessListener {
                Log.d("Users", "User deleted from list")
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }
}

interface OnGetUsersDataListener {
    fun onUsersReceived(users: List<UserInf>)

    fun onFailedToReceiveUsers(ex: Exception)
}

interface OnGetUserListener {
    fun onUserReceived(user: UserInf)

    fun onFailedToReceiveUser(ex: Exception)
}

