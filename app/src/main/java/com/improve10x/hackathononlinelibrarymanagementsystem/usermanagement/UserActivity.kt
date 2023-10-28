package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.AddBookActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityUserBinding

class UserActivity : BaseActivity() {
    private val network = UserNetwork.getInstance();
    private var binding: ActivityUserBinding? = null
    private var usersAdapter: UsersAdapter? = null
    private var userList = mutableListOf<UserInf>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupInvoiceListAdapter()
        setupInvoiceListRv()
        setupFabBtn();
    }

    private fun setupFabBtn() {
        binding?.userFab?.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Sellers"
        getUserList()

    }

    private fun getUserList() {
        network.getUsers(object : OnGetUsersDataListener {
            override fun onUsersReceived(users: List<UserInf>) {
                userList.clear()
                userList.addAll(users)
                usersAdapter?.setData(userList)
            }

            override fun onFailedToReceiveUsers(ex: Exception) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setupInvoiceListAdapter() {
        usersAdapter = UsersAdapter()
        usersAdapter!!.setData(userList)
    }

    private fun setupInvoiceListRv() {
        binding?.booksRv?.setLayoutManager(LinearLayoutManager(this))
        binding?.booksRv?.setAdapter(usersAdapter)
        usersAdapter?.setOnItemActionListener(object :OnUserItemActionListener {
            override fun onEditClicked(user: UserInf?) {
                val intent = Intent(this@UserActivity, AddUserActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            override fun onDeleteClicked(user: UserInf?) {
                if (user != null) {
                    network.deleteUser(user)
                    getUserList()
                };
            }
        })
    }


}