package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.R
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.AddBookActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.BookActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {
    private var binding : ActivityLauncherBinding? = null
    private val userNetwork : UserNetwork = UserNetwork.getInstance()
    private var role : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        UserMgr.getInstance()
        handleAdminBtn()
        handleSellerBtn()
        handleBuyerBtn()
    }

    private fun setUser(onGetUserListener: OnGetUserListener) {
        var userId = when(role){
            "Buyer" -> "yHej4LNx5KZOwSzQmU5D"
            "Seller" -> "gnYx48aKGpGB0b1ktKd9"
            "Admin" -> "emDHBiFipB1sZbbi8RWD"
            else -> "yHej4LNx5KZOwSzQmU5D"
        }
        userNetwork.getUser(userId, role, onGetUserListener)
    }

    private fun handleBuyerBtn() {
        binding?.buyerBtn?.setOnClickListener {
            role = "Buyer"
            setUser(object : OnGetUserListener {
                override fun onUserReceived(user: UserInf) {
                    val intent = Intent(this@LauncherActivity, BookActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailedToReceiveUser(ex: Exception) {
                    Log.e("Online Library : " + "LauncherActivity", ex.message, ex)
                }
            })
        }
    }

    private fun handleSellerBtn() {
        binding?.sellerBtn?.setOnClickListener {
            role = "Seller"
            setUser(object : OnGetUserListener {
                override fun onUserReceived(user: UserInf) {
                    val intent = Intent(this@LauncherActivity, BookActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailedToReceiveUser(ex: Exception) {
                    Log.e("Online Library : " + "LauncherActivity", ex.message, ex)
                }
            })
        }
    }

    private fun handleAdminBtn() {
        binding?.adminBtn?.setOnClickListener {
            role = "Admin"
            setUser(object : OnGetUserListener {
                override fun onUserReceived(user: UserInf) {
                    val intent = Intent(this@LauncherActivity, UserActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailedToReceiveUser(ex: Exception) {
                    Log.e("Online Library : " + "LauncherActivity", ex.message, ex)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Online Library"
    }
}