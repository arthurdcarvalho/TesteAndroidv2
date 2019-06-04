package br.com.arthur.altranteste.data

import android.os.AsyncTask
import android.support.annotation.WorkerThread
import br.com.arthur.altranteste.data.local.UserDao
import br.com.arthur.altranteste.model.User
import br.com.arthur.altranteste.service.API
import br.com.arthur.altranteste.service.Service
import br.com.arthur.altranteste.service.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository(val userDao: UserDao) {

    private lateinit var service: API

    @WorkerThread
    fun login(request: LoginRequest) {
        service = Service.getService()
        val call = service.login(request)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                when {
                    response.isSuccessful -> {
                        response.body().let {
                            UserAsyncTask(userDao).execute(it?.userAccount)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })

    }


    private class UserAsyncTask internal constructor(
        private val mAsyncTaskDao: UserDao
    ) : AsyncTask<User.UserAccount, Void, Void>() {

        override fun doInBackground(vararg params: User.UserAccount): Void? {
            mAsyncTaskDao.save(params[0])
            return null
        }
    }

}