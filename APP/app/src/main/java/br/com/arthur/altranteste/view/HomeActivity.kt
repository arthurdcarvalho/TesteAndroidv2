package br.com.arthur.altranteste.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.arthur.altranteste.R
import br.com.arthur.altranteste.data.UserViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private var userId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = UserViewModel(application)
        viewModel.getUser().observe(this, Observer { user ->
            userId = user?.userId
        })
    }
}
