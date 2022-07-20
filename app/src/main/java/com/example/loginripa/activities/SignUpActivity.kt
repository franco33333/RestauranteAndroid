package com.example.loginripa.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.loginripa.R
import com.example.loginripa.activities.data.entity.User
import com.example.loginripa.activities.data.sharedPreference.SharedPreferencesManager
import com.example.loginripa.activities.utils.Utils.setToolBar
import com.example.loginripa.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setToolBar(this@SignUpActivity, binding.tbToolbar)

        var name: String
        var surname: String
        var username: String
        var email: String
        var password: String
        binding.btSignup.setOnClickListener {
            val valid = validateForm()
            if (valid) {
                name = binding.etName.text.toString()
                surname = binding.etSurname.text.toString()
                username = binding.etUsername.text.toString()
                email = binding.etEmail.text.toString()
                password = binding.etPassword.text.toString()

                val user = User(name, surname, username, email, password)

                SharedPreferencesManager.saveUser(this, user)
                val i = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(i)
            }
        }

        binding.cbCheck.setOnCheckedChangeListener { _, _ ->
            binding.btSignup.isEnabled = !binding.btSignup.isEnabled
        }
    }

    private fun validateForm(): Boolean {
        var esValido = true
        if (binding.etName.text?.isEmpty() == true) {
            binding.etName.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etSurname.text?.isEmpty() == true) {
            binding.etSurname.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etUsername.text?.isEmpty() == true) {
            binding.etUsername.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etEmail.text?.isEmpty() == true) {
            binding.etEmail.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etPassword.text?.isEmpty() == true) {
            binding.etPassword.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etConfirm.text?.isEmpty() == true) {
            binding.etConfirm.error = getString(R.string.errorEmpty)
            esValido = false
        }
        if (binding.etConfirm.text.toString() != binding.etPassword.text.toString()) {
            binding.etConfirm.error = getString(R.string.errorPasswordMatch)
            esValido = false
        }
        return esValido
    }
}