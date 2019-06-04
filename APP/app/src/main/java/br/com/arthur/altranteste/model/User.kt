package br.com.arthur.altranteste.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {

    @SerializedName("userAccount")
    var userAccount: UserAccount? = null

    @SerializedName("error")
    var error: Error? = null

    @Entity(tableName = "T_USER_ACCOUNT")
    class UserAccount : Serializable {
        @PrimaryKey
        @ColumnInfo(name = "ID")
        @SerializedName("userId")
        var userId: Int = 0

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("bankAccount")
        lateinit var bankAccount: String

        @SerializedName("agency")
        lateinit var agency: String

        @SerializedName("balance")
        var balance: Double = 0.0
    }

    class Error {
        @SerializedName("code")
        var code: Int = 0

        @SerializedName("message")
        lateinit var message: String
    }

}