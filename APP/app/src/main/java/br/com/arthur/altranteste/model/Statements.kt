package br.com.arthur.altranteste.model

import com.google.gson.annotations.SerializedName

class Statements {

    @SerializedName("statementList")
    lateinit var statementList: List<Statement>

    @SerializedName("error")
    private lateinit var error: Error

    class Statement {
        @SerializedName("title")
        private lateinit var title: String

        @SerializedName("desc")
        private lateinit var desc: String

        @SerializedName("date")
        private lateinit var date: String

        @SerializedName("value")
        private var value: Double = 0.0
    }

    class Error

}