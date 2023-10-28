package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityAddBookBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityAddUserBinding

class AddUserActivity : BaseActivity() {
    private var binding: ActivityAddUserBinding? = null
    private val network = UserNetwork.getInstance()
    private var user : UserInf? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val intent = intent
        user = intent.getSerializableExtra("user") as? UserInf
        user?.let {
            populateFields(user!!)
        }
        setupTickFabClick()
    }

    private fun populateFields(user: UserInf) {
        supportActionBar?.title = "Edit User"
        binding?.userIdEd?.setText(user.id)
        binding?.nameEd?.setText(user.name)
        binding?.roleRg?.findViewWithTag<RadioButton>(user.role)?.isChecked = true
        binding?.addressEd?.setText(user.address)
        binding?.mobileNumberEd?.setText(user.mobile)
        binding?.emailEd?.setText(user.email)
        binding?.panEd?.setText(user.PAN)
        binding?.GstNoEd?.setText(user.GSTNo)
        binding?.registeredDateEd?.setText(user.registeredDate)
        binding?.isActiveCb?.isChecked = user.isActive
    }

    private fun setupTickFabClick() {
        binding?.tickFab?.setOnClickListener {
            if(user == null) {
                saveUser(user = createUser());
            } else {
                updateUser(user!!)
            }
        }
    }

    private fun updateUser(user: UserInf) {
        val updatedUser = createUser().copy(id = user.id)
        network.updateUser(updatedUser)
        finishAndMove()
    }

    private fun createUser(): UserInf {
        val role = (findViewById<RadioButton>(binding!!.roleRg!!.checkedRadioButtonId)).text
        return UserInf(
             name = binding?.nameEd?.text.toString(),
             address = binding?.addressEd?.text.toString(),
             role =  role.toString(),
             mobile =  binding?.mobileNumberEd?.text.toString(),
             email =  binding?.emailEd?.text.toString(),
             PAN =  binding?.panEd?.text.toString(),
             GSTNo =  binding?.GstNoEd?.text.toString(),
             registeredDate =  binding?.registeredDateEd?.text.toString(),
             isActive =  binding?.isActiveCb?.isChecked!!,
            )
    }

    private fun saveUser(user : UserInf) {
        network.addUser(user)
        finishAndMove()
    }

    private fun finishAndMove() {
        finish()
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if(user == null){
            supportActionBar?.title = "Add User"
        }
    }

}
