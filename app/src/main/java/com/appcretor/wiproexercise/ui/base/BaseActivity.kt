package com.appcretor.wiproexercise.ui.base


import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity :AppCompatActivity () {

    abstract fun setup()

    lateinit var context: Context

    protected fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        context=this
    }

    protected fun showToast(msg: String, isLengthLong: Boolean = false) {
        if (isLengthLong) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showSnackBar(view: View, message: String, isLengthLong: Boolean = false) {
        if (isLengthLong) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show()
            return
        }
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .show()
    }
    protected fun showSnackBarWithOK(view: View, message: String, isLengthLong: Boolean = false) {
        if (isLengthLong) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show()
            return
        }
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .show()
    }


}
