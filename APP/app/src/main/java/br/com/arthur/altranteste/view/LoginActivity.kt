package br.com.arthur.altranteste.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import br.com.arthur.altranteste.R
import br.com.arthur.altranteste.data.UserViewModel
import br.com.arthur.altranteste.databinding.ActivityLoginBinding
import br.com.arthur.altranteste.service.request.LoginRequest
import br.com.arthur.altranteste.util.PasswordRules

class LoginActivity : BaseView() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        configurePasswordRules()
        configureClicks()
    }

    private fun configureClicks() {
        loginBinding.loginButton.setOnClickListener {
            val viewModel = UserViewModel(application)
            val user = loginBinding.inputUser.text.toString()
            val password = loginBinding.inputPassword.text.toString()

            viewModel.login(LoginRequest(user, password)).observe(this, Observer { userData ->
                dismissLoadingDialog()
                if (userData != null) {
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro no Login", Toast.LENGTH_SHORT).show()
                }
            })

            showLoadingDialog()
        }
    }

    private fun configurePasswordRules() {
        PasswordRules(
            loginBinding.inputPassword,
            object : PasswordRules.Listener {
                override fun isStrong() {
                    loginBinding.loginButton.isEnabled = true
                    loginBinding.containerPassword.isErrorEnabled = false
                }

                override fun isNotStrong(error: String) {
                    loginBinding.loginButton.isEnabled = false
                    loginBinding.containerPassword.error = error
                }

            })
    }
}