package br.com.arthur.altranteste.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.arthur.altranteste.data.local.BankDatabase
import br.com.arthur.altranteste.data.local.UserDao
import br.com.arthur.altranteste.model.User
import br.com.arthur.altranteste.service.request.LoginRequest

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: UserRepository
    private lateinit var userLiveData: LiveData<User.UserAccount>
    private lateinit var userAccount: LiveData<User.UserAccount>

    private val userDao: UserDao

    init {
        userDao = BankDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun login(loginRequest: LoginRequest): LiveData<User.UserAccount> {
        repository.login(loginRequest)
        return userDao.getUser()
    }

    fun getUser(): LiveData<User.UserAccount> {
        return userDao.getUser()
    }

    fun getUserId(): LiveData<User.UserAccount> {
        userAccount = userDao.getUserById(1)
        return userAccount
    }

}