package br.com.arthur.altranteste.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.arthur.altranteste.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User.UserAccount)

    @Query("SELECT * FROM T_USER_ACCOUNT")
    fun getUser(): LiveData<User.UserAccount>

    @Query("SELECT * FROM T_USER_ACCOUNT U WHERE U.ID= :id")
    fun getUserById(id: Int): LiveData<User.UserAccount>

    @Delete
    fun deleteUser(user: User.UserAccount)

}