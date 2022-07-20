package com.example.loginripa.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.User
import com.example.loginripa.activities.data.sharedPreference.SharedPreferencesManager
import com.example.loginripa.activities.utils.Utils.setToolBar
import com.example.loginripa.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setToolBar(this@LoginActivity, binding.tbToolbar)

        val user: User? = SharedPreferencesManager.getUser(this)
        user?.let {
            binding.etUsername.setText(user.username)
            binding.etPassword.setText(user.password)
        }

        binding.btLogin.setOnClickListener {
            binding.errorEtUsername.text = ""
            binding.errorEtPassword.text = ""

            if (validateFields(user)) {
                val i = Intent(this@LoginActivity, HomeActivity::class.java)
                i.putExtra(HomeActivity.USER_KEY, user)
                startActivity(i)
            }
        }
    }

    private fun validateFields(user: User?): Boolean {
        var invalido = false
        if (binding.etUsername.text.toString().isEmpty()) {
            binding.errorEtUsername.text = getString(R.string.errorEmpty)
            invalido = true
        }
        if (binding.etPassword.text.toString().isEmpty()) {
            binding.errorEtPassword.text = getString(R.string.errorEmpty)
            invalido = true
        }
        if (user == null) {
            if (!invalido) {
                binding.errorEtUsername.text = getString(R.string.errorIncorrect)
                binding.errorEtPassword.text = getString(R.string.errorIncorrect)
            }
            return false
        }
        if (!invalido) {
            if (user.username != binding.etUsername.text.toString() ||
                user.password != binding.etPassword.text.toString()
            ) {
                binding.errorEtUsername.text = getString(R.string.errorIncorrect)
                binding.errorEtPassword.text = getString(R.string.errorIncorrect)
                return false
            }
        }
        return !invalido
    }
}