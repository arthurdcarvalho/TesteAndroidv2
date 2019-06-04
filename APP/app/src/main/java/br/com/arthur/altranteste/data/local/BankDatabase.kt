package br.com.arthur.altranteste.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.arthur.altranteste.model.User

@Database(entities = [User.UserAccount::class], version = 1, exportSchema = false)
abstract class BankDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: BankDatabase? = null

        fun getDatabase(context: Context): BankDatabase {
            val bankInstance = INSTANCE
            if (bankInstance != null) {
                return bankInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BankDatabase::class.java,
                    "Bank_Database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}