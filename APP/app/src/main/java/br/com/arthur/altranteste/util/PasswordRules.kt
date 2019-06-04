package br.com.arthur.altranteste.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PasswordRules(private val edit: EditText, private val listener: Listener) : TextWatcher {
    private var haveNumber = false
    private var haveLowerCase = false
    private var haveUpperCase = false
    private var haveSymbols = false

    init {
        edit.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val password = edit.text.toString()
        isStrongPassword(password)
    }

    private fun isStrongPassword(password: String) {
        clearBooleanTags()
        checkCharacters(password)

        if (!haveNumber) {
            listener.isNotStrong("Sem número")
        } else if (!haveLowerCase) {
            listener.isNotStrong("Sem letra minúscula")
        } else if (!haveUpperCase) {
            listener.isNotStrong("Sem letra maiuscula")
        } else if (!haveSymbols) {
            listener.isNotStrong("Sem caractere especial")
        } else {
            listener.isStrong()
        }
    }

    private fun checkCharacters(senha: String) {
        for (c in senha.toCharArray()) {
            when (c) {
                in '0'..'9' -> haveNumber = true
                in 'A'..'Z' -> haveLowerCase = true
                in 'a'..'z' -> haveUpperCase = true
                else -> haveSymbols = true
            }
        }
    }

    private fun clearBooleanTags() {
        haveNumber = false
        haveLowerCase = false
        haveUpperCase = false
        haveSymbols = false
    }

    interface Listener {
        fun isStrong()

        fun isNotStrong(error: String)
    }

}