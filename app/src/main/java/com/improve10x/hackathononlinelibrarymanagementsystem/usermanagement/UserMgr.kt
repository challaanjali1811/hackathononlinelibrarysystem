package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import java.io.Serializable

class UserMgr private constructor() {
    companion object {
        private var network: UserNetwork? = null

        @Volatile
        private var instance: UserMgr? = null

        fun getInstance() {
            instance ?: synchronized(this) {
                instance ?: UserMgr().also {
                    instance = it
                    network = UserNetwork.getInstance()
                    network!!.getUser()
                }
            }
        }

        fun getCurrentUser(): UserInf? {
            return network?.currentUser
        }
    }

}