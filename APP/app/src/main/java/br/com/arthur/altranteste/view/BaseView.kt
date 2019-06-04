package br.com.arthur.altranteste.view

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import br.com.arthur.altranteste.R

abstract class BaseView : AppCompatActivity() {

    private lateinit var loadingDialog: ProgressDialog

    protected fun showLoadingDialog() {
        loadingDialog = ProgressDialog.show(this, "", getString(R.string.carregando), true)
        loadingDialog.setCancelable(true)
    }

    protected fun dismissLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

}